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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


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

	/***
	 * This is an identity function that can be used to change phantom types, e.g.
	 * 
	 * <pre><code>
	 * pure native freeze frege.runtime.Runtime.our :: Mutable s a -> a
	 * </code></pre>
	 * 
	 * Often used to change a mutable value to an immutable one without copying,
	 * this is safe if the mutable value cannot be accessed anymore, as in
	 * the array fromList functions - here, a new mutable array is created locally and
	 * filled with elements from a list, and then the same array object is returned
	 * as immutable.mi-25-aug
	 * 
	 * 
	 * @param o some value
	 * @return the value passed as argument, but Frege thinks it is different.
	 */
    public final static<T> T our(final T o) { return o; }

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
    *  <p> Start a program to run in a fork/join task </p>
    *    *
    *  <p> Called from the java <tt>main</tt> method of a frege program.
    *  This converts the argument String array to a list and passes this to
    *  the compiled frege main function. The result is an IO action of type
    *  <tt>IO ()</tt> to which then <tt>IO.performUnsafe</tt> is applied.
    *  The resulting {@link Lambda} then actually executes the frege code
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
    *  @param val a {@link Lambda} value to be evaluated in a fork/join context
    */
	public static void runMain(final Object arg) {
		final Lazy val = Delayed.delayed(arg);
		java.util.concurrent.Callable<Object> action = new java.util.concurrent.Callable<Object>() {
			public Object call() {
				return val.<Object> forced();
			}
		};
		// Check if parallel execution is prohibited
		// This is the case when the VM was started with -Dfrege.parallel=x
		// and x is not equal, ignoring case, to the string "true".
		final String prop = System.getProperty("frege.parallel");
		final boolean parallel = prop == null ? true : Boolean.valueOf(prop);
		if (!parallel) {                             // comment out for java6
			// execution outside fork/join pool
			try {
				action.call();
			} catch (Exception ex) {
				throw new Error(ex); // ex.printStackTrace();
			}
			return;
		} 
		// comment following block for java6
		// run in fork/join pool
		try { // comment try/catch blocks out for java6
			new java.util.concurrent.ForkJoinPool(2 * java.lang.Runtime
					.getRuntime().availableProcessors()).submit(action).get();
		} catch (Exception ex) {
			throw new Error(ex);
		}
		return;
	}
	final public static boolean fork(Lambda it) {
		Lazy a = it.apply(true).result();
	    if (java.util.concurrent.ForkJoinTask.inForkJoinPool())
	    	java.util.concurrent.ForkJoinTask.adapt(a).fork();
	    return true;
	}
}
