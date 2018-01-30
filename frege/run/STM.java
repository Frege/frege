package frege.run;

public final class STM {

    private static final class TVarValue {
        final Object value;

        TVarValue(final Object value) {
            this.value = value;
        }
    }

    private static final class TVar implements Comparable<TVar> {

        private TVarValue value;

        private final static java.util.concurrent.atomic.AtomicInteger tvarIds =
                new java.util.concurrent.atomic.AtomicInteger(0);

        private final int id = tvarIds.getAndIncrement();

        private final java.util.concurrent.locks.ReadWriteLock lock =
                new java.util.concurrent.locks.ReentrantReadWriteLock();

        private final java.util.Queue<java.util.concurrent.Semaphore> waitQ =
                new java.util.concurrent.ConcurrentLinkedQueue<>();

        TVar(final Object value) {
            this.value = new TVarValue(value);
        }

        final Object read() {
            return this.value.value;
        }

        final void write(final Object value) {
            this.value = new TVarValue(value);
        }

        TVarValue getContainer() {
            return value;
        }

        final void enqueue(java.util.concurrent.Semaphore c) {
            waitQ.add(c);
        }

        final void release() {
            for (java.util.concurrent.Semaphore e : waitQ) {
                e.release();
            }
            waitQ.clear();
        }

        final java.util.concurrent.locks.Lock readLock() {
            return lock.readLock();
        }

        final java.util.concurrent.locks.Lock writeLock() {
            return lock.writeLock();
        }

        public final boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (o instanceof TVar) {
                return this.id == ((TVar) o).id;
            }
            return false;
        }

        public final int hashCode() {
            return this.id;
        }

        public final int compareTo(final TVar other) {
            return this.id - other.id;
        }
    }

    private static final class TransactionLogEntry {
        private final TVar ref;
        private final TVarValue oldValue;
        private TVarValue newValue;

        TransactionLogEntry(final TVar tvar) {
            this.ref = tvar;
            this.oldValue = tvar.getContainer();
            this.newValue = this.oldValue;
        }

        final void setNewValue(final Object value) {
            this.newValue = new TVarValue(value);
        }

        final TVar getRef() {
            return this.ref;
        }

        final Object getNewValue() {
            return this.newValue.value;
        }

        final boolean isValid() {
            return this.ref.getContainer() == this.oldValue;
        }

        final boolean changed() {
            return oldValue != newValue;
        }

        final java.util.concurrent.locks.Lock readLock() {
            return this.ref.readLock();
        }

        final java.util.concurrent.locks.Lock writeLock() {
            return this.ref.writeLock();
        }
    }

    private static final class TransactionLog {

        private final java.util.Map<TVar, TransactionLogEntry> log =
                new java.util.TreeMap<>();

        private final java.util.Set<java.util.concurrent.locks.Lock> locked =
                new java.util.HashSet<>();

        private final TransactionLog parent;

        TransactionLog() {
            this.parent = null;
        }

        private TransactionLog(final TransactionLog parent) {
            this.parent = parent;
        }

        final Object readTVar(final TVar tvar) {
            final TransactionLogEntry present = lookup(tvar);
            if (present == null) {
                final TransactionLogEntry e = new TransactionLogEntry(tvar);
                getRoot().log.put(tvar, e);
                return e.getNewValue();
            } else {
                return present.getNewValue();
            }
        }

        final void writeTVar(final TVar tvar, final Object value) {
            if (log.containsKey(tvar)) {
                final TransactionLogEntry e = log.get(tvar);
                e.setNewValue(value);
            } else {
                if (parent != null) {
                    getRoot().log.putIfAbsent(tvar, new TransactionLogEntry(tvar));
                }
                final TransactionLogEntry e = new TransactionLogEntry(tvar);
                e.setNewValue(value);
                log.put(tvar, e);
            }
        }

        final boolean commit() {
            assert(parent == null);
            try {
                if (validateAndLock()) {
                    doCommit();
                    wakeUpWaiters();
                    return true;
                } else {
                    return false;
                }
            } finally {
                unlock();
            }
        }

        private final TransactionLog getRoot() {
            TransactionLog root = this;
            while (root.parent != null) {
                root = root.parent;
            }
            return root;
        }

        final TransactionLogEntry lookup(final TVar tvar) {
            final TransactionLogEntry e = log.get(tvar);
            if (e == null) {
                if (parent == null) {
                    return null;
                } else {
                    return parent.lookup(tvar);
                }
            } else {
                return e;
            }
        }

        private final void doCommit() {
            for (TransactionLogEntry e : log.values()) {
                if (e.changed()) {
                    TVar tvar = e.getRef();
                    tvar.write(e.getNewValue());
                }
            }
        }

        private final void wakeUpWaiters() {
            for (TransactionLogEntry e : log.values()) {
                if (e.changed()) {
                    e.getRef().release();
                }
            }
        }

        private boolean validateAndLock() {
            locked.clear();
            for (TransactionLogEntry e : log.values()) {
                final java.util.concurrent.locks.Lock l =
                        e.changed()
                            ? e.writeLock()
                            : e.readLock();
                l.lock();
                locked.add(l);
                if ((e.isValid()) == false) {
                    return false;
                }
            }
            return true;
        }

        private void unlock() {
            for (java.util.concurrent.locks.Lock e : locked) {
                e.unlock();
            }
        }

        final void retry() throws InterruptedException {
            assert(parent == null);
            final java.util.concurrent.Semaphore c = new java.util.concurrent.Semaphore(0);
            final boolean waiting = doRetry(c);
            if (waiting) {
                c.acquire();
            }
        }

        private final boolean doRetry(java.util.concurrent.Semaphore c)
                throws InterruptedException {
            try {
                final boolean valid = validateAndLock();
                if (valid) {
                    for (TVar e : log.keySet()) {
                        e.enqueue(c);
                    }
                }
                return valid;
            } finally {
                unlock();
            }
        }

        final TransactionLog startNested() {
            return new TransactionLog(this);
        }

        final TransactionLog retryNested() {
            assert(parent != null);
            return parent;
        }

        final TransactionLog mergeNested() {
            assert(parent != null);
            parent.merge(log);
            return parent;
        }

        final void merge(final java.util.Map<TVar, TransactionLogEntry> childLog) {
            log.putAll(childLog);
        }
    }

    private static final ThreadLocal<TransactionLog> threadLocalLog =
        new ThreadLocal<TransactionLog>() {
            @Override
            protected TransactionLog initialValue() {
                return new TransactionLog();
            }
        };

    public final static class NativeTVar<T> {

        final TVar tvar;

        public NativeTVar(final T value) {
            final TVar tvar = new TVar(value);
            this.tvar = tvar;
        }
    }

    public static final class Functions {
        @SuppressWarnings("unchecked")
        public static final <T> T readTVar(final NativeTVar<T> tvar) {
            final TransactionLog log = threadLocalLog.get();
            Object value = log.readTVar(tvar.tvar);
            return ((T) value);
        }
        public static final <T> void writeTVar(final NativeTVar<T> tvar, final T value) {
            final TransactionLog log = threadLocalLog.get();
            log.writeTVar(tvar.tvar, value);
        }
        public static final boolean commit() {
            final TransactionLog log = threadLocalLog.get();
            return log.commit();
        }
        public static final void resetLog() {
            threadLocalLog.remove();
        }
        public static final void block() throws InterruptedException {
            final TransactionLog log = threadLocalLog.get();
            log.retry();
        }
        public static final void startNested() {
            final TransactionLog log = threadLocalLog.get();
            threadLocalLog.set(log.startNested());
        }
        public static final void retryNested() {
            final TransactionLog log = threadLocalLog.get();
            threadLocalLog.set(log.retryNested());
        }
        public static final void mergeNested() {
            final TransactionLog log = threadLocalLog.get();
            threadLocalLog.set(log.mergeNested());
        }
    }
}
