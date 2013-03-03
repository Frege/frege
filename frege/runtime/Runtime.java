/*
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
 
 */

package frege.runtime;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;


/**
 * Miscellanous functions and methods needed in frege.
 * 
 * @author ingo
 *
 */
public class Runtime {

	/**
	 * Implementation for 
	 * <code>pure native constructor frege.runtime.Runtime.constructor :: a -> Int</code>
	 */
	final public static int constructor(Value v) {		// if it is statically known that v is a Value 
		return v._constructor(); 
	}
	// final public static int constructor(Integer v) { return v; }
	final public static int constructor(Object v) { 	// if v is completely unknown, it could still be a Value
		if (v instanceof Value) return ((Value)v)._constructor();
		if (v instanceof Integer) return ((Integer)v).intValue();
		return 0;
	}
	
	/**
	 * Implementation for frege.prelude.PreludeBase.getClass
	 */
	final public static Class<?> getClass(Object o) {
		return o.getClass();
	}
	
	/**
	 * Provide UTF-8 encoded standard printer for stdout with automatic line flushing
	 */
	public static PrintWriter stdout = new PrintWriter(
			new OutputStreamWriter(System.out, StandardCharsets.UTF_8),
			true);
	
	/**
	 * Provide UTF-8 encoded standard printer for stderr with autoflush
	 */
	public static PrintWriter stderr = new PrintWriter(
			new OutputStreamWriter(System.err, StandardCharsets.UTF_8),
			true);
	
	/**
	 * Provide UTF-8 decoded standard inupt Reader
	 */
	public static BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in, StandardCharsets.UTF_8));
	
    /**
     * <p> Utility method used by <code>String.show</code> to quote a string. </p>
     */
    final public static java.lang.String quoteStr(java.lang.String a) {
		java.lang.StringBuilder sr = new java.lang.StringBuilder(2+((5*a.length()) >> 2));
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
     * <p> Utility method used by <code>Char.show</code> to quote a character. </p>
     */
	final public static java.lang.String quoteChr(char c) {
        java.lang.StringBuilder sr = new java.lang.StringBuilder(8);
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

	/*
     * <p> This is used to copy a mutable value. </p>
     * <p> The returned value is of same type, but is considered immutable.
     * Suitable for instances of classes that do implement {@link Cloneable}.</p> 
     * <p> Remember that only a deep copy makes a really safe copy .</p> 
     * @param o the value to copy
     * @return a copy obtained by cloning of the argument value, followed by deserialization
     */
    // public final static<T extends Cloneable> T clone(final T o) { return o.clone(); }

    /**
     * <p> This is used to copy a mutable value. </p>
     * <p> The returned value is of same type, but is considered immutable.
     * Suitable for instances of classes that do not implement {@link Cloneable}, 
     * but do implement {@link Serializable}.</p> 
     * @param o the value to copy
     * @return a copy obtained by serialization of the argument value, followed by deserialization
     */
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

    /**
     * <p> Cheat with phantom types. </p>
     * <p> This method is announced with type String a -&gt; Int -&gt; a
     * but it always returns a char.</p>
     * <p> This is fine as long as nobody is able to actually create a
     * value with type, say String Int. <br>
     * This could be done only with another malicious native function, though.</p>
     */
    public final static Object itemAt(final String value, final int offset) {
        return value.charAt(offset);
    }
    /**
     * <p> The empty polymorphic value of type StringJ a </p>
     * <p> Referenced in frege.prelude.List.ListLike_StringJ
     */
   final public static String emptyString = "";
    
   /**
    *  <p> Run a IO action as main program. </p>
    *    *
    *  <p> Called from the java <tt>main</tt> method of a frege program.
    *  This converts the argument String array to a list and passes this to
    *  the compiled frege main function. The result is an IO action of type
    *  <tt>IO ()</tt> to which then <tt>IO.performUnsafe</tt> is applied.
    *  The resulting {@link Lambda} then actually executes the frege code
    *  when evaluated.</p>
    *
    */
	public static void runMain(final Object arg) {
		try {
			Delayed.delayed(arg).call();
		}
//		catch (Exception ex) {
//			throw new Error(ex); // ex.printStackTrace();
//		}
		finally {
			stderr.flush();
			stdout.flush();
		}
		return;
	}
	
	final public static void exit() {
//		stdout.close();
//		stderr.close();
	}
	
	/**
	 * The fork/join pool for ad hoc parallelism used by Frege runtime system..
	 * 
	 * @see fork(), setFregeForkJoindPool()
	 */
	private static java.util.concurrent.ForkJoinPool fjpool = null;
	
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
		if (fjpool == null) {
			fjpool = pool;
		}
		return fjpool;
	}
	
	/**
	 *  <p>Evaluate <code>e</code> in <code>const e</code> in parallel. 
	 *  This is a helper function for the `par` operator.</p>
	 *  
	 *  <p>Applies some value to the argument (which must be a {@link Lambda} that ignores
	 *  that argument), to abtain a  {@link Delayed} value.</p>
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
	 *  <p>A {@link Delayed} has the property that it prevents itself from being evaluated
	 *  more than once. It also blocks threads that attempt parallel execution. 
	 *  Once evaluated, it remembers the result and subsequent invokactions of 
	 *  {@link Delayed#call()} get the evaluated value immediatedly.</p>
	 *  
	 *  <p>The success of parallel evaluation therefore depends on the time between 
	 *  construction of the delayed expression and the time 
	 *  when the value will actually be used. Ideally, it takes some CPU ressources to 
	 *  evaluate a parallel computation, and it so happens that the value is only needed
	 *  after it has been evaluated, to avoid wait time in the main thread.</p>  

     *  @param val a {@link Lambda} value to be evaluated in a fork/join context
     *  @return true
	 * 
	 */
	final public static boolean fork(Lambda val) {
		Lazy a = val.apply(true).result();
	    if (java.util.concurrent.ForkJoinTask.inForkJoinPool())
	    	java.util.concurrent.ForkJoinTask.adapt(a).fork();
	    else synchronized (emptyString) {		// make sure 2 threads can't do that
	    	if (fjpool == null) {				// at the same time
	    		fjpool = new java.util.concurrent.ForkJoinPool(
	    					2 * java.lang.Runtime.getRuntime().availableProcessors());
	    	}
	    	fjpool.submit(a);
	    }
	    return true;
	}
	
	final public static void waitFor(Object it) throws InterruptedException {
		synchronized (it) {
			it.wait();
		}
	}
	
	final public static void notifyOne(Object it) {
		synchronized (it) {
			it.notify();
		}
	}
	
	final public static void notifyAll(Object it) {
		synchronized (it) {
			it.notifyAll();
		}
	}
	/**
	 * set this if you need a different exit code
	 */
	private static volatile int exitCode = 0;
	final public static void setExitCode(int a) {
		synchronized (stdin) {
			exitCode = a > exitCode && a < 256 ? a : exitCode; 
		}
	}
}


