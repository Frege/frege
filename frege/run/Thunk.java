/*
    Copyright © 2011 - 2015, Ingo Wechsung
    All rights reserved.
    
    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:
    
        Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.
    
        Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution. 
        
        Neither the name of the copyright holder
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


package frege.run;

import frege.runtime.BlackHole;

/**
 * <p> A lazy value that can be shared and is updated with the evaluated value.</p>
 *
 * <p> Lazy values differ from proper values insofar as they need to know
 * how to exactly evaluate themselves.
 * This is the job of the {@link Thunk#call}. </p>

 * <p> The functionality of {@link Thunk#call} is fixed through <tt>final</tt>
 * and cannot be changed. It is in some sense the heart of the runtime, as it provides
 * support for tail calls and lazy evaluation. </p>
 * 
 * <p>{@link Thunk#call} repeatedly invokes {@link Lazy#call} until the returned value is 
 * in <i>weak head normal form</i>, which is signalled by not being an instance of {@link Lazy}.
 * In addition, the result is shared through 
 * caching it in a private instance variable. Hence, instances of
 * {@link Thunk} are not immutable and this in turn requires synchronization.</p>

 * <p> The caching of the evaluated value would not be strictly necessary, as
 * repeated evaluation must always yield the same result. Experience shows,
 * however, that re-evaluations are quite common, hence not caching the result
 * would most likely cause even the simplest program to perform unacceptably bad.
 * </p>

 * <h3> Introduction of lazyness in frege compiled code </h3>
 *
 * <p> Thunk values are introduced at only 2 occasions: </p>
 *
 * <ol>
 * <li>
 * <p>  Thunk arguments of data constructors or functions. Take the following example: </p>
 * <pre><code>
 * -- the list of fibonacci numbers
 * fibs = 0:1:zipWith (+) fibs (tail fibs)
 * </code></pre>
 *
 * <p> The second argument of (:) must be lazy because its type is recursive. 
 * Types of constructor arguments are said to be recursive, if they mention a type 
 * that depends on the type of the constructed value.
 * A type depends on itself and on types that mention it in at least one constructor argument. 
 * </p>
 *
 * <p>Hence, the application of <tt>zipWith</tt> argument may appear in code like this:</p>
 * <pre><code>
 * new Thunk(() -> zipWith(plusFunc, fibs, tail(fibs));
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
 * a {@link Thunk} object instead of actually evaluating it themselves. This way, a long
 * series of tail calls can be converted to a while loop, see {@link Thunk#call}. </p>
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
public class Thunk<R> implements Lazy<R> {
	/* INVARIANT: exactly one of item and eval is null at any time */
	private volatile Object item = null;
	private Lazy<R> eval;
	
	/**
	 * <p> Create a thunk from a {@link Lazy} value. </p>
	 * <p> On evaluation via {@link Thunk#call}, this Thunk will be updated with
	 * the result of evaluating the value. </p>
	 * @param it a lazy value. <b>This must never be null!</b>
	 */
	Thunk(Lazy<R> it) { eval = it; }
	/**
	 * <p>Create a Thunk from some Object.</p>
	 * <p>It is checked wether the argument is, in fact, a {@link Lazy}, and if so, the
	 * behaviour is just as with the other constructor. If, however, the argument is an
	 * ordinary value, this Thunk will only wrap it and return it when called. </p> 
	 * @param it a possibly lazy value. <b>This must never be null!</b>
	 */
	@SuppressWarnings("unchecked")
	Thunk(Object it) {
		if (it instanceof Lazy) {
			eval = (Lazy<R>)it;
		}
		else {
			item = it;
			eval = null;
		}
	}
	
	/** 
	 * <p> evaluate the {@link Lazy}, and update this Thunk, unless it is already evaluated. </p>
	 * @return the evaluated value 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final synchronized R call() {
		if (item != null) 
			// value already computed
			return (R)item;
		// Detect black holes
		// When the same thread evaluates this while we are not yet done,
		// it will return the black hole, and this will, in turn,
		// give a Class Cast Exception later.
		// Different threads will have to wait anyway due to the "synchronized".
		// item = BlackHole.it;
		Object o = eval.call();
		Lazy<R>  t = null;
		// algebraic datatypes are instances of Lazy, but their call() is the identity
		// hence we know when to finish if either 
		// * the result o is not Lazy 
		// * or if it is the same reference as eval.
		while (o  instanceof Lazy && (t = (Lazy<R>)o) != eval) {
			eval = t;
			o = eval.call();
		}
		item = o;
		eval = null;	// make sure all the closed over things are not referenced anymore
		return (R)item;
	}

	
	/**
	 * <p>Evaluate an object if it is a lazy value.</p>
	 * <p>
	 * This method is intended for use in generated code, as the compiler
	 * hopefully never errs about the expected type.</p>
	 * 
	 * <p>It handles multiple levels of laziness, that is, if a {@link Lazy} returns another
	 * <i>different</i> {@link Lazy}, this one will be evaluated in turn, and so forth.
	 * This helps in cases when we need the result of a function that does tail calls.
	 * The tail call is returned as unshared {@link Lazy}, and {@link Lazy#call()}-ing this might just
	 * return the next tail call. 
	 * </p>
	 * @throws ClassCastException if the argument is not an instance of {@link Lazy} 
	 *         and cannot be casted to <code>R</code>
	 * @throws ClassCastException if the argument is an instance of {@link Lazy} and the
	 *         delivered value cannot be casted to <code>R</code>         
	 * @param o the object in question
	 * @return the evaluated value if the argument is a {@link Lazy} instance, 
	 *         otherwise the argument itself.  
	 *         The result is conveniently casted to the expected return type <code>R</code>.
	 * @author ingo
	 */
	@SuppressWarnings("unchecked")
	public final static<R> R forced(Object o) {
		Object r = null;
		while (o instanceof Lazy) { 
			r = ((Lazy<R>)o).call();
			if (r==o) break;
			o = r;
		}
		return (R) o;
	}
	
	
	/**
	 * Make sure we have a {@link Lazy} value.
	 * 
	 * <p>This is, in a sense, the exact opposite of {@link Thunk#call}. 
	 * Whereas the latter evaluates a {@link Thunk}, unless already evaluated,
	 * this method constructs a {@link Thunk} value unless it is already a {@link Lazy} 
	 * one.</p>
	 * 
	 * <p>Because all {@link Algebraic} types implement {@link Lazy}, 
	 * this will create a wrapper for native values only.</p>
	 * 
	 *   @param  val some value
	 *   @return If the argument is already {@link Lazy}, it is returned properly casted.
	 *           Otherwise it is wrapped in a {@link Thunk.Value} and returned.
	 *   @author ingo 
	 */
	@SuppressWarnings("unchecked")
	public final static<X>  Lazy<X> delayed(X val) {
		if (val instanceof Lazy) return (Lazy<X>) val;
		return new Thunk<X>(val);
	}
	
	/**
	 * <p>Static form of the constructor</p>
	 
	 * <p>For statically known {@link Thunk}s, this is the identity.</p>
	 * <p>{@link Lazy} instances are wrapped in a {@link Thunk}, this makes them shared.</p>
	 * <p>Other things are passed to the constructor. In any case, the returned value is
	 * a compile time {@link Thunk}.</p>
	 * 
	 * @return a {@link Thunk}, no matter what.
	 */
	public final static<R> Thunk<R> shared(Thunk<R> v)  { return v; }
	public final static<R> Thunk<R> shared(Lazy<R> v)   { return new Thunk<R>(v); }
	public final static<R> Thunk<R> shared(R v) 		{ return new Thunk<R>(v); }
	
	/***
	 * <p>Utility function to get some value and clearing it at the same time.</p>
	 * <p>Use like</p>
	 * <code>
	 * Thunk.once(v, v = null)
	 * </code>
	 * @param obj the desired value, usually given as a nonfinal variable
	 * @param nul this argument is ignored, usually used to assign null to the variable
	 * @return the first argument
	 * 
	 */
	public final static<X> X once(X obj, X nul) { return obj; }
}
