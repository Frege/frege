/* «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»

    Copyright © 2011, Ingo Wechsung
    All rights reserved.

    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:

        Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.

        Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution. Neither the name of the copyright holder
        nor the names of its contributors may be used to endorse or
        promote products derived from this software without specific
        prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE
    COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
    PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
    OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
    SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
    LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
    USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
    AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
    LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
    IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
    THE POSSIBILITY OF SUCH DAMAGE.

    «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•» */

package frege;

import frege.rt.*;
import java.io.*;

/*
 * $Author$
 * $Date$
 * $Revision$
 * $Id$
 */
/**
 *  <p> Utility functions for frege code. </p>
 */
public abstract class RT {
    /**
     * Get the constructor
     */
    @SuppressWarnings("unchecked")
	public final static int constructor(Object x) {
        if (x instanceof FV) { return ((FV)x).constructor(); }
        else if (x instanceof Value)
            return ((Value) x)._c();
        else if (x instanceof Lazy)
            return constructor(((Lazy<FV>) x)._e());
        else throw new Error("constructor: " + x.getClass().getName());
    }

    /**
     * <p> Copy a {@link java.util.regex.Matcher} object. </p>
     *
     * <p> Most matcher operations implemented in
     * {@link java.util.regex.Matcher} change the state of the
     * matcher instance.
     * Therefore we can't use them directly in frege code. </p>
     *
     * <p> Instead, we provide here a re-implementation of the functionality
     * we need. The idea is to make a copy of the matcher <b>before</b> the
     * destructive operation, run the operation on the copy and return it
     * to frege code. This is safe because there is no reference to the
     * copy before the operation in frege code.
     * <br>As a consequence, many matcher operations appear in frege as
     * function from Matcher to Matcher or Maybe Matcher.
     * </p>
     *
     * <p>This function relies on the fact that
     * {@link java.util.regex.Matcher#toMatchResult} effectively makes a
     * copy (yet an incomplete one) of the current matcher. It completes the copy by invoking
     * {@link java.util.regex.Matcher#usePattern}
     * which sets up the internal arrays.</p>
     *
     * <p>The copy can not be used to retrieve information about the last match
     * operation of the original matcher.</p>
     *
     *
     * @return a copy of a matcher
     */

    public static java.util.regex.Matcher clone(final java.util.regex.Matcher m) {
        return ((java.util.regex.Matcher)m.toMatchResult()).usePattern(m.pattern());
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#usePattern
     * @return the copy the operation was performed on
     */
    public static java.util.regex.Matcher usePattern(final java.util.regex.Matcher m,
                                                     final java.util.regex.Pattern p) {
        return ((java.util.regex.Matcher)m.toMatchResult()).usePattern(p);
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#useAnchoringBounds
     * @return the copy the operation was performed on
     */
    public static java.util.regex.Matcher useAnchoringBounds(final java.util.regex.Matcher m,
                                                             final boolean b) {
        final java.util.regex.Matcher c = clone(m);
        c.useAnchoringBounds(b);
        return c;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#matches
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher matches(final java.util.regex.Matcher m) {
        final java.util.regex.Matcher c = clone(m);
        if (c.matches()) return c;
        return null;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#find
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher find(final java.util.regex.Matcher m) {
        final java.util.regex.Matcher c = clone(m);
        if (c.find()) return c;
        return null;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#usePattern
     * @see java.util.regex.Matcher#find
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher usePatternAndFind(final java.util.regex.Matcher m,
                                                            final java.util.regex.Pattern p) {
        final java.util.regex.Matcher c = usePattern(m, p);
        final boolean emptymatch = m.start(0) == m.end(0);
        if (emptymatch)
            if (c.find(m.end(0))) return c; else return null;
        if (c.find()) return c;
        return null;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#replaceFirst
     * @return a string
     */
    public static java.lang.String replaceFirst(final java.util.regex.Matcher m,
                                                final java.lang.String r) {
        return clone(m).replaceFirst(r);
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#replaceAll
     * @return a string
     */
    public static java.lang.String replaceAll(final java.util.regex.Matcher m,
                                                final java.lang.String r) {
        return clone(m).replaceAll(r);
    }

    /**
     * <p> Exception thrown when the undefined value is evaluated. </p>
     */
    public static class Undefined extends java.lang.IllegalArgumentException {
        /**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public Undefined(String err) {
            super(err);
        }
        public Undefined(String err, Throwable cause) {
            super(err, cause);
        }
        /** <p>This function actually never returns. </p> */
	@SuppressWarnings("unused")
        public final boolean die()  { if (true) throw this; return false; }
    }
    /**
     * <p> Exception thrown when all matches of a case or lambda fail. </p>
     */
    public static class NoMatch extends Undefined {
        /**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public NoMatch(String qname, int line, Object x) {
            super(qname + " at line " + line + " no match for value " + x);
        }
    }
    /**
     * <p> Exception thrown when a guard on a pattern binding fails. </p>
     */
    public static class GuardFailed extends Undefined {
        /**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public GuardFailed(String qname, int line) {
            super(qname + " at line " + line + ": guard failed.");
        }
    }
    /**
     * <p> Utility method used by String.show to quote a string. </p>
     */
    final public static java.lang.String quoteStr(java.lang.String a) {
		java.lang.StringBuilder sr = new java.lang.StringBuilder();
		sr.append('"');
		int i = 0;
		char c;
		while (i<a.length()) {
			c = a.charAt(i++);
			if (c<' ' || c == '\177') {
				sr.append('\\');
				sr.append(java.lang.String.format("%o", (int) c));
			}
			else if (c == '\\' || c == '"') {
				sr.append('\\');
				sr.append(c);
			}
			else sr.append(c);
		}
		sr.append('"');
		return sr.toString();
	}

    /**
     * <p> Utility method used by Char.show to quote a character. </p>
     */
	final public static java.lang.String quoteChr(char c) {
        java.lang.StringBuilder sr = new java.lang.StringBuilder();
        sr.append("'");
        if (c<' ' || c == '\177') {
            sr.append('\\');
            sr.append(java.lang.String.format("%o", (int) c));
        }
        else if (c == '\\' || c == '\'') {
            sr.append('\\');
            sr.append(c);
        }
        else sr.append(c);
        sr.append("'");
        return sr.toString();
    }

    public final static<T> T our(final T o) { return o; }

    public final static<T extends Serializable> T copySerializable(final T o) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            final ObjectInputStream ois = new ObjectInputStream(bais);
            @SuppressWarnings("unchecked")
            final T s = (T) ois.readObject();
            ois.close();
            return s;
        } catch (Exception exc) {
            new Undefined("error in (de)serialization", exc).die();
            return null;
        }
    }

    /* ----------- code for fork/join support  ------------- */
    /**
     *  <p> Start a program to run in a fork/join task </p>
     *
     *
     *  <p> Called from the java <tt>main</tt> method of a frege program.
     *  This converts the argument String array to a list and passes this to
     *  the compiled frege main function. The result is an IO action of type
     *  <tt>IO ()</tt> to which then <tt>IO.performUnsafe</tt> is applied.
     *  The resulting {@link frege.rt.Unknown} then actually executes the frege code
     *  when evaluated.</p>
     *
     *  <p>This method checks first if the system property <tt>frege.parallel</tt>
     *  is set to a value that does not represent <tt>true</tt>.
     *  If so, it just evaluates the argument.
     *  Otherwise it submits its argument to a {@link java.util.concurrent.ForkJoinPool}
     *  and waits for completion. This ensures that frege code sees itself executed
     *  in a fork join pool and is able to fork further tasks.
     *  </p>
     *  <p>In frege code that is not executed in a fork join pool all library
     *  functions for ad hoc parallelism shall perform semantically equivalent
     *  sequential functions
     *  with as little overhead as possible.
     *  </p>
     *
     *  @param val a {@link frege.rt.Lazy} value to be evaluated in a fork/join context
     */
    public static<V> void fjMain(final Lazy<V> val) {
        java.util.concurrent.Callable<V> action = new java.util.concurrent.Callable<V> () {
            public V call() { return val._e(); }
        };
        // Check if parallel execution is prohibited
        // This is the case when the VM was started with -Dfrege.parallel=x
        // and x is not equal, ignoring case, to the string "true".
        final String  prop     = System.getProperty("frege.parallel");
        final boolean parallel = prop == null ? true : Boolean.valueOf( prop );
        if ( !parallel ) {							// comment out for java6
            // execution outside fork/join pool
            try {
                action.call();
            } catch (Exception ex) {
                throw new Error(ex);        // ex.printStackTrace();
            }
            return;
        }											// comment out for java6
        // run in fork/join pool
        try {										// comment try/catch blocks out for java6
            new java.util.concurrent.ForkJoinPool(
                        2*Runtime.getRuntime().availableProcessors())
                .submit(action)
                .get();
        } catch (Exception ex) {
            throw new Error(ex);
        }
        return;
    }

    /**
     * <p> fork execution of an Unknown </p>
     * <p> If we are in a fork/join pool and the argument contains an unevaluated value
     * evaluation of this value is started in a new fork/join task. </p>
     * @param it A product of arity 2 that contains the value that must be evaluated in member m1
     * @return <tt>true</tt>
     */
    public final static boolean fork(final frege.rt.Prod2 it) {
        if (java.util.concurrent.ForkJoinTask.inForkJoinPool()
            && it.mem1 instanceof frege.rt.Unknown
            && it.mem1._u()) {
                final frege.rt.Unknown<FV> u = (frege.rt.Unknown<FV>) it.mem1;
                java.util.concurrent.ForkJoinTask.<FV>adapt(u).fork();
                return true;
        }
        return true;
    }

    /**
     * <p> Cheat with phantom types. </p>
     * <p> This method is announced with type String a -&gt; Int -&gt; a
     * but it always returns a (boxed) char.</p>
     * <p> This is fine as long as nobody is able to actually create a
     * value with type, say String Int. <br>
     * This could be done only with another malicious native function, though.</p>
     */
    public final static Box.Char itemAt(final String value, final int offset) {
        return Box.Char.mk(value.charAt(offset));
    }
    /**
     * <p> The empty polymorphic value of type StringJ a </p>
     * <p> Referenced in frege.prelude.List.ListLike_StringJ
     */
   final public static String emptyString = "";
     
}
