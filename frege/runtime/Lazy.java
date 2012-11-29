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

import java.util.concurrent.Callable;

/**
 * <p> Base class for lazy values.</p>
 *
 * <p> Lazy values differ from proper values insofar as they need to know
 * how to exactly evaluate themselves.
 * This is the job of the {@link Lazy#call} and {@link Lazy#eval} functions. </p>

 * <p> The functionality of {@link Lazy#call} is fixed through <tt>final</tt>
 * and cannot be changed. It is in some sense the heart of the runtime, as it provides
 * support for tail calls and lazy evaluation. </p>
 * 
 * <p>{@link Lazy#call} repeatedly invokes {@link Lazy#eval} until the value is 
 * in <i>weak head normal form</i>, which is signalled by not being an instance of {@link Lazy}.
 * In addition, the result is shared through 
 * caching it in a private instance variable. Hence, instances of
 * Unknown are not immutable and this in turn requires synchronization.</p>

 * <p> The caching of the evaluated value would not be strictly necessary, as
 * repeated evaluation must always yield the same result. Experience shows,
 * however, that re-evaluations are quite common, hence not caching the result
 * would most likely cause even the simplest program to perform unacceptably bad.
 * </p>

 * <p> The real work, then, is done by {@link Lazy#eval}. It may return a proper value or
 * another lazy value, though it is required that the latter is somehow closer
 * to the final result, so that evaluation of that value gets even closer
 * and repeated evaluation of the indermediate lazy values
 * eventually reaches the result. </p>
 *
 * <h3> Introduction of lazyness in frege compiled code </h3>
 *
 * <p>This version of the frege runtime supports a mostly eager execution
 * where all functions are strict in all arguments by default 
 * and data constructors are strict in all arguments that are not recursive.</p>
 * 
 * <p> Lazy values are introduced at only 2 occasions: </p>
 *
 * <ol>
 * <li>
 * <p>  Lazy arguments of data constructors. Take the following example: </p>
 * <pre><code>
 * -- the list of fibonacci numbers
 * fibs = 0:1:zipWith (+) fibs (tail fibs)
 * </code></pre>
 *
 * <p> The second argument of (:) is lazy because its type is recursive. 
 * Types of constructor arguments are said to be recursive, if they mention a type 
 * that depends on the type of the constructed value.
 * A type depends on itself and on types that mention it in at least one constructor argument. </p>
 *
 * <p>Hence, the application of <tt>zipWith</tt> argument may appear in code like this:</p>
 * <pre><code>
 * new Lazy() {
 *     final public TList eval() {
 * 	        return zipWith(plusFunc, fibs, tail(fibs));
 *     }
 * }
 * </code></pre>
 * </li>
 * <li>
 * <p> Non-recursive tail calls. </p>
 * <p> If the return value of a function is expressed as a function call,
 * we call this a <em>tail call</em>. If a function tail-calls itself, this is
 * <em>tail recursion</em>. Here are examples: </p>
 * <pre>
 * <code>
 * even 0 = true
 * even n = odd  (n-1)
 * odd  0 = false
 * odd  n = even (n-1)
 * factorial n | n &gt;= 0 = helper 1 n where
 *    helper r 0 = r
 *    helper r n = helper (r*n) (n-1)
 * </code>
 * </pre>
 * <p> Function "helper" is tail recursive and will be compiled to a while loop.
 * Functions "factorial", "even" and "odd", however, employ tail calls.
 * In "factorial", this is not a problem, it just sets up parameters for the
 * very efficient "helper" function, thus we can expect a maximum stack depth of 2
 * no matter what the value of "n" is.</p>
 * <p> Things are different with "even" and "odd" that would need stack space
 * proportional to the value of their argument if implemented naively.
 * But frege functions may simply return their tail call in the form of
 * a {@link Lazy} object instead of actually evaluating it themselves. This way, a long
 * series of tail calls can be converted to a while loop, see {@link Lazy#call}. </p>
 * <p>Unfortunately, this does not work in every case. Take the even/odd exmple in a slightly
 * changed form:</p>
 * <pre>
 * even 0 = true
 * even 1 = false
 * even n = odd  (n-1)
 * odd  n = !(even n)
 * </pre>
 * This will very soon run out of stack space with greater n. To see why, imagine <tt>even 5</tt>
 * had to be evaluated.
 * <pre>
 * even 5 = odd 4               by 3rd rule of even
 * odd 4 = !(even 4)            by definition of odd
 * !(even 4) = !(odd 3)         by 3rd rule of even
 * !(odd 3)  = !(!(even 3))
 * !(!(even 3)) = !(!(odd 2))
 * !(!(odd 2))  = !(!(!(even 2)))
 * !(!(!(even 2))) = !(!(!(odd 1)))
 * !(!(!(odd 1))) = !(!(!(!(even 0))))
 * !(!(!(!(even 0)))) = !(!(!(!true)))
 * </pre>
 * <p>
 * Because ! must first know the value to negate it must evaluate its argument
 * before it returns. Hence, evaluation of (odd n) results in n nested negations, i.e. in
 * a stack depth of n.
 * </p>
 * <p>This effect happens always when the recursion is done indirectly in an
 * argument of a strict function. Another well-known case is the naive implementation
 * of a function that tells a lists's length:</p>
 * <pre>
 * naiveLength [] = 0
 * naiveLength (_:as) = 1 + naiveLength as
 * </pre>
 * <p>Here, the recursion takes place in the 2nd argument of the addition.
 * To add 1 to something, one must first know to what, hence the
 * whole list must be consulted before even the first addition 
 * can take place.</p>
 * <p>Compare the better implementation:</p>
 * <pre>
 * length xs = helper xs 0
 *    where
 *       helper []     !count = count
 *       helper (_:as) !count = helper as (count+1)
 * </pre>
 * Here, only tail call recursion takes place and because counter is strict, the increment
 * will be evaluated in each loop.
 * </li>
 * </ol>
 *
 * @author ingo
 *
 */
public abstract class Lazy implements Applicable, Callable<Object> {

	private volatile Object item = null;
	
	/**
	 * <p> Compute and cache the value by repeatedly invoking {@link Lazy#eval}, 
	 * or return previously cached result. </p>
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public final synchronized Object call() {
		if (item != null) 
			// value already computed
			return item;
		Object o = eval();
		while (o  instanceof Lazy) {
			o = ((Lazy) o).eval();
		}
		item = o;
		return o;
	}

	/**
	 * A shorthand for <code>((Applyable) x.call()).apply(arg)</code>
	 * 
	 * @throws ClassCastException unless this lazy value evaluates to a {@link Lambda}
	 * @return the result of applying the evaluated value to the argument
	 * @see frege.runtime.Applicable#apply(java.lang.Object)
	 */
	@Override
	public Applicable apply(Object arg) {
		return ((Applicable) call()).apply(arg);
	}

	/***
	 * <p> This is the method subclasses of {@link Lazy} must implement. </p>
	 * @return a value that is the final evaluated result of this lazy value, 
	 *         or another lazy value that is somewhat closer to the result (e.g. a tail call)  
	 */
	abstract public Object eval();
}
