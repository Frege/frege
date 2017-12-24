/**
 * 
 */
package frege.run;

import java.util.concurrent.Callable;


/**
 * Support for concurrency and parallelism.
 * 
 * @author ingo
 *
 */
public class Concurrent {

	private static Object lock = new Object();

	/**
     * The fork/join pool for ad hoc parallelism used by Frege runtime system..
     *
     * @see Concurrent#fork Concurrent#setFregeForkJoinPool
     */
   private static java.util.concurrent.ForkJoinPool fjpool = new java.util.concurrent.ForkJoinPool(
			2 * java.lang.Runtime.getRuntime().availableProcessors());

	/**
     * <p>Set the {@link java.util.concurrent.ForkJoinPool} that should be used by the
     * Frege runtime.</p>
     * 
     * If the fork/join pool of the Frege runtime is not yet set, 
     * it will be initialized with the argument. If, however, Frege's
     * fork/join pool is already in use, it will not be given up.
     * 
     * This should be used by external code that maintains its own fork/join pool
     * <b>before</b> calling into Frege code
     * to prevent creation of another fork/join pool by the Frege runtime.
     * 
     * Note that it is not necessary to do this when Frege code is called from code 
     * that itself runs in a fork/join pool. But, in any case, it does no harm.  
     * 
     * @param pool - a fork/join pool to use
     * @return The fork/join pool the Frege runtime will actually use. 
     */
    public static java.util.concurrent.ForkJoinPool
    		setFregeForkJoinPool(java.util.concurrent.ForkJoinPool pool) {
    	synchronized (lock) {
    		if (fjpool == null) {
    			fjpool = pool;
    		}
    		return fjpool;
    	}
    }

    /**
     * <p>An executor service to run forkIO threads</p>
     */
    private static java.util.concurrent.ExecutorService execService = null;
    
    /**
     * <p>Set the {@link java.util.concurrent.ExecutorService} that 
     * should be used to run <i>forkIO</i> threads.</p>
     * 
     * If the executor service of the Frege runtime is not yet set, 
     * it will be initialized with the argument. If, however, Frege's
     * executor service is already in use, it will not be given up.
     * It is thus possible to pass a <code>null</code> to query the
     * current executor service.
     * 
     * This should be used by external code that maintains its own 
     * executor service
     * <b>before</b> calling into Frege code
     * to prevent creation of another executor service by the Frege runtime.
     * 
     * If the {@link Concurrent#executorService} finds no executor service, it will
     * create a fixed {@link java.util.concurrent.ThreadPoolExecutor} with a maximum
     * number of threads that equals 2 times the number of CPU cores available.
     *      
     * @param svc - an executor service to use
     * @return The executor service the Frege runtime will actually use. 
     */
    public static java.util.concurrent.ExecutorService
    		setFregeExecutorService(java.util.concurrent.ExecutorService svc) {
    	synchronized (lock) {
    		if (execService == null) {
    			execService = svc;
    		}
    		return execService;
    	}
    }
    
	/**
     *  <p>Evaluate <code>e</code> in <code>const e</code> in parallel. 
     *  This is a helper function for the `par` operator.</p>
     *  
     *  <p>The argument is passed as {@link Thunk} or {@link Lazy}, which both
     *  extend {@link Callable}.</p>
     *  
     *  <p>It then checks if we run in a {@link java.util.concurrent.ForkJoinPool}.
     *  If this is so, it {@link java.util.concurrent.ForkJoinTask#fork()}s, causing the 
     *  delayed value to be evaluated asynchronously.</p>
     *  
     *  <p>If we're not running in a {@link java.util.concurrent.ForkJoinPool}, 
     *  it checks if the pool 
     *  of the Frege runtime is already initialized. 
     *  If this is not the case, a new {@link java.util.concurrent.ForkJoinPool} 
     *  will be created.</p>
     *  
     *  <p>Finally, the delayed value will be submitted to the fork/join pool 
     *  for asynchronous execution.</p>
     *  
     *  <p>A {@link Thunk} has the property that it prevents itself from being evaluated
     *  more than once. It also blocks threads that attempt parallel execution. 
     *  Once evaluated, it remembers the result and subsequent invocations of
     *  {@link Thunk#call()} get the evaluated value immediately.</p>
     *  
     *  <p>The success of parallel evaluation therefore depends on the time between 
     *  construction of the delayed expression and the time 
     *  when the value will actually be used. Ideally, it takes some CPU resources to
     *  evaluate a parallel computation, and it so happens that the value is only needed
     *  after it has been evaluated, to avoid wait time in the main thread.</p>  
    
     *  @param val a {@link Thunk} value to be evaluated in a fork/join context. 
     *  		The Frege type system makes sure this is a pure computation.
     *  @return true
     * 
     */
    final public static<A> boolean fork(final Callable<A> it) {
        if (java.util.concurrent.ForkJoinTask.inForkJoinPool())
        	java.util.concurrent.ForkJoinTask.adapt(it).fork();
        else {
        	fjpool.submit(it);
        }
        return true;
    }

    /**
     * <p>Give access to the current executor service.</p>
     * 
     * <p>If the Frege executor service {@link Concurrent#execService} is not
     * yet initialized, creates a fixed thread pool executor.</p>
     * 
     *  @return the executor service
     */
    final public static java.util.concurrent.ExecutorService executorService() {
    	return (execService == null) ?
    		setFregeExecutorService(
    				java.util.concurrent.Executors.newFixedThreadPool(
    						2 * java.lang.Runtime.getRuntime().availableProcessors()))
    		: execService;
    }
    
    /**
     * <p>Shutdown the Frege Executor Service if it exists.</p>
     */
    final public static void shutDownIfExists() {
    	if (execService != null && !execService.isShutdown()) {
    		execService.shutdown();
    	}
    }
    
    /**
     * <p>Monitor wait on a given object.</p>
     * <p>Because {@link Object#wait} must be run in a synchronized block,
     * we cannot just introduce it as a native function in Frege.</p>
     * <p>Basically, Frege does not know something like object identity.
     * It is, however, guaranteed that, in the presence of a top level definition like:</p>
     * <pre>
     * object = "object to wait on"
     * </pre>
     * <p>the name <code>object</code> will always refer to the same string object.</p>
     * 
     * <p>
     * It is possible, that two top level values that evaluate to the same string value:</p>
     * <pre>
     * obj1 = "object"
     * obj2 = "object"
     * </pre>
     * <p>could also be the same object at runtime, hence use different string constants if
     * you want guaranteed different objects.</p> 
     * 
     * @param it - some object
     * @throws InterruptedException
     * @author ingo
     */
	final public static void waitFor(Object it) throws InterruptedException {
    	synchronized (it) {
    		it.wait();
    	}
    }

	/**
	 * <p>Notify exactly one thread that is waiting on the object</p>
	 * 
	 * <p>Because {@link Object#notify()} must be run in a synchronized block,
	 * we cannot just introduce this as native function in Frege.</p>
	 * 
	 * <p>See the discussion for  {@link Concurrent#waitFor(Object)}</p>
	 * @param it - some object
	 * @author ingo
	 */
	final public static void notifyOne(Object it) {
    	synchronized (it) {
    		it.notify();
    	}
    }

	/**
	 * <p>Notify all threads that are waiting on the object</p>
	 * 
	 * <p>Because {@link Object#notifyAll()} must be run in a synchronized block,
	 * we cannot just introduce this as native function in Frege.</p>
	 * 
	 * <p>See the discussion for  {@link Concurrent#waitFor(Object)}</p>
	 * @param it - some object
	 * @author ingo
	 */
	final public static void notifyAll(Object it) {
    	synchronized (it) {
    		it.notifyAll();
    	}
    }

}
