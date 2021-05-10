/*
    Copyright © 2011 - 2021, Ingo Wechsung
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


package frege.run7;

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

 * <h3> Introduction of laziness in frege compiled code </h3>
 *
 * <p> Thunk values are introduced at only 2 occasions: </p>
 *
 * <ol>
 * <li>
 * <p>  Thunk arguments of data constructors or functions. Take the following example: </p>
 * <pre><code>
 * -- the list of Fibonacci numbers
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
	private Thunk(Lazy<R> it) { eval = it; }
	// only used internally
	private Thunk()   { eval = null; }

	@Override
	public Thunk<R> asThunk() { return this; }
	
	@Override
	public boolean isShared() { return true; }

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
		item = BlackHole.it;
		
		Thunk<R> that;
		R rx;
		Lazy<R> rl;
		// algebraic datatypes are instances of Lazy, but their call() is the identity
		// hence we know when to finish if either 
		// * the result is not Lazy 
		// * or if it is the same reference as eval.
		while (true) {
			that = eval.asThunk();
			if (that != null) {
				// We detected nested thunks. It would be foolish to evaluate them with
				// call(), because chances are that in the process of doing this another
				// nested Thunk arises, so that the call depth increases, in the worst
				// case until stack overflow.
				// Note that if someone manages to construct a loop of Thunks,
				// the current one counts as evaluated because of the BlackHole in item!
				if (that.isEvaluated()) {
					item = that.item;		// if this is the same Thunk, this will be the black hole
											// otherwise a valid result.
					eval = null;
					return (R)item;
				}
				// at this point, that != this and that is not evaluated yet
				// We simply short cut, instead of calling!
				eval = that.eval;
				continue;					// check again for nested thunk
			}
			// At this point we have a Lazy that is not a Thunk at all
			// So here is where Frege code actually may execute.
			rx = eval.call();
			if (rx instanceof Lazy) {
				rl = (Lazy<R>)(Object) rx;
				if (rl != eval) {
					eval = rl;
					continue;						// it returned a different Lazy
				}
			}
			// at this point, we have a value in rx that is either
			// - not a Lazy, so it is a value
			// - or the same as eval, hence an algebraic value or a function
			// so we mark the Thunk as evaluated, remember and return the value
			eval = null;
			item = rx;
			return rx;
		}
	}

	/**
	 * <p>tell if this Thunk is already evaluated.</p>
	 * @return true, if it is already evaluated, false otherwise
	 */
	public boolean isEvaluated() {
		return item != null;
	}
	
	
	/**
	 * Make sure we have a {@link Lazy} value.
	 * 
	 * <p>This is, in a sense, the exact opposite of {@link Thunk#call}. 
	 * Whereas the latter evaluates a {@link Thunk}, unless already evaluated,
	 * this method constructs a {@link Lazy} value unless it is statically known
	 * to be a {@link Lazy}. 
	 * </p>
	 * 
	 * <p>Because all algebraic types implement {@link Lazy}, 
	 * this should create an extra wrapper for native values only.</p>
	 * 
	 *   @param  val some value
	 *   @return If the argument is already {@link Lazy}, it is returned properly casted.
	 *           Otherwise it is wrapped in a {@link Box} and returned.
	 *   @author ingo 
	 */
	public final static Lazy<Boolean> 	lazy(boolean val) 	{ return new Box.B(val); }
	public final static Lazy<Byte>    	lazy(byte val) 		{ return new Box.O(val); }
	public final static Lazy<Short>   	lazy(short val) 	{ return new Box.S(val); }
	public final static Lazy<Character> lazy(char val) 		{ return new Box.C(val); }
	public final static Lazy<Integer> 	lazy(int val) 		{ return new Box.I(val); }
	public final static Lazy<Long>    	lazy(long val) 		{ return new Box.L(val); }
	public final static Lazy<Double>  	lazy(double val) 	{ return new Box.D(val); }
	public final static Lazy<Float>   	lazy(float val) 	{ return new Box.F(val); }
	public final static<X>  Lazy<X>   	lazy(Lazy<X> val) 	{ return val; }
	public final static<X>  Lazy<X>   	lazy(X val) 		{ return new Box<X>(val); }
	
	/**
	 * <p>Static form of the constructor</p>
	 
	 * <p>For  {@link Lazy} values that are already shared, this is the identity.</p>
	 * <p>Other {@link Lazy} instances are wrapped in a {@link Thunk}, this makes them shared.</p>
	 * 
	 * @return a shared version of the {@link Lazy} value.
	 */
	public final static<R> Lazy<R> shared(Lazy<R> v)   {
		return v.isShared() ? v : new Thunk<R>(v); 
	}
	
	/**
	 * <p> The following creates a Thunk«B» and hence a Lazy«B»
	 * from something that is known to be Lazy«Lazy«B»». </p>
	 * 
	 * <p>Now, the cast is justified for the reason that a Thunk«B» arranges for evaluation
	 * in such a way that a B will actually be returned. Hence, while Thunk«B» and Thunk«Lazy«B»»
	 * are indeed different types, it is the case that call() must never return a Lazy«B» 
	 * that is not also a B (like with algebraic values).</p>
	 * 
	 * <p>We need this when two or more functions are mutually recursive, like in the 
	 * following scenario:</p>
	 * <pre>
	 * 	even 0 = true
	 *  even 1 = false
	 *  even n = odd (n-1)
	 *  odd n = even (n-1)
	 * </pre>
	 * 
	 * <p>In such cases, direct method calls would cause a StackOverflow on larger numbers.
	 * Hence, it is mandatory, that the result must be Lazy for all functions. 
	 * Now consider the case where odd() is about to call even(): it must return the same 
	 * value as even(n-1) without actually calling even(). Hence it should return something like:<p>
	 *  
	 * <pre>return (Lazy«Boolean») (() -> even(n-1));</pre> 
	 * 
	 * <p>but since the right hand side is already Lazy«Boolean» this doesn't type check. 
	 * Likewise</p>
	 * 
	 * <pre>return (Lazy«Lazy«Boolean»») (() -> even(n-1));</pre>
	 * 
	 * <p>is incommensurable with the return type, which is just Lazy«Boolean». Hence the
	 * solution is to embed the lambda in a Thunk:</p>
	 * 
	 * <pre>return Thunk.«B»nested( (Lazy«Lazy«Boolean»») (() -> even(n-1)) );</pre>
	 * 
	 * <p>thereby lowering the compile type laziness of the result by one level.</p>
	 * <p>Furthermore, odd() can now be seen as a tail-call safe function, since it doesn't
	 * actually do a tail call, but just constructs the thunk.
	 * 
	 * This, in turn, makes it now possible for even to call  odd(n-1) method directly, yet
	 * without evaluating the result. For, this will just give the nested thunk after a
	 * limited number of method calls (1, in this case), which is
	 * a lazy boolean and can be returned right away.</p>
	 * <p>An alternative to this method would be to have 3 categories of functions: strict ones, lazy ones and 
	 * double lazy ones. But this would dramatically complicate the code generator.</p> 
	 */
	@SuppressWarnings("unchecked")
	public final static<R> Lazy<R> nested(Lazy<Lazy<R>> v) { 
		return (Thunk<R>) new Thunk<Lazy<R>>(v); 
	}
	
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
	
	/**
	 * <p> Some often used lazy values. </p>
	 */
	public final static Lazy<Boolean> lazyTrue  = lazy(true);
	public final static Lazy<Boolean> lazyFalse = lazy(false);
	public final static Lazy<String>  lazyemptyString = lazy("");
	public final static Lazy<Integer> lazyZero = lazy(0);
	public final static Lazy<Integer> lazyOne = lazy(1);
	public final static Lazy<Integer> lazyTwo = lazy(2);
	public final static Lazy<frege.runtime.Phantom.RealWorld> lazyWorld = lazy(frege.runtime.Phantom.theRealWorld);
	public final static Lazy<Short> lazyUnit = lazy((short)0);
}
