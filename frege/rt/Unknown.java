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
    
package frege.rt;

// $Author$
// $Date$
// $Rev$
// $Id$

/**
 * <p> Base class for lazy values.</p>
 *
 * <p> Lazy values differ from proper values insofar as they need to know
 * how to exactly evaluate themselves.
 * This is the job of the _e() and _v() functions. </p>

 * <p> The functionality of {@link Unknown#_e} is fixed through <tt>final</tt>
 * and cannot be changed.
 * It calls {@link Unknown#_v} in a loop until the value is fully evaluated and then
 * caches it in a private instance variable. Hence, instances of
 * Unknown are not immutable and this in turn requires synchronization.</p>

 * <p> The caching of the evaluated value would not be strictly necessary, as
 * repeated evaluation must always yield the same result. Experience shows,
 * however, that re-evaluations are quite common, hence not caching the result
 * would most likely cause even the simplest program to perform unacceptably bad.
 * </p>

 * <p> The real work, then, is done by _v(). _v() may return a proper value or
 * another lazy value, though it is required that the latter is somehow closer
 * to the final result, so that evaluation of that value gets even closer
 * and repeated evaluation of the indermediate lazy values
 * eventually reaches the result. </p>
 *
 * <h3> Introduction of lazyness in frege compiled code </h3>
 *
 * <p> Lazy values are introduced at 3 occasions: </p>
 *
 * <ol>
 * <li>
 * <p>  Code for self referential values. Take the following example: </p>
 * <pre>
 * // the list of fibonacci numbers
 * fibs = 0:1:zipWith (+) fibs (tail fibs)
 * </pre>
 *
 * <p> One cannot just compile this thus: </p>
 *
 * <pre>
 *  static final TList<Bint> fibs = DCons.<Bint>inst.run(box(0),
 *      DCons.<Bint>inst().run(box(1),
 *          Prelude.<Bint, Bint>zipWith().run(Prelude.INum_Int._plus(),
 *                                        fibs, Prelude.<Bint>tail().run(fibs))));
 * </pre>
 * <p>
 * The problem is that the java variable <tt>fibs</tt> is not yet assigned
 * at the time it is passed to <tt>zipWith</tt>.
 * <br>The solution is: </p>
 * <pre>
 * static final Lazy<TList<Bint>> fibs = new Unknown<TList<Bint>>() {
 *      final Lazy<TList<PI>> _v() {
 *          return DCons.<Bint>inst().run(box(0),
 *                  DCons.<Bint>inst().run(box(1),
 *                      Prelude.<Bint, Bint>zipWith().run(Prelude.INum_Int._plus(),
 *                                        fibs, Prelude.<Bint>tail().run(fibs))));
 *      }
 * }
 * </pre>
 * <p>In this way, evaluation of <tt>fibs</tt> is avoided unless it is really needed and
 * the reference is assigned a non-null value.
 * </li>
 * <li> Code for non-strict arguments
 *
 * <p> Here is a short explanation to clarify what a non-strict argument is.
 * </p>
 *
 * <p> Many functions have the property that they do not always need all their
 * arguments to compute their return value. A classic example that should be
 * familiar to Java programmers is the function performed by the &amp;&amp;
 * operator: if the left operand is <tt>false</tt>, there is no need to evaluate
 * the right operand because the result is <tt>false</tt>
 * no matter what the boolean value
 * of the right operand is. </p>
 *
 * <p> It is important to note that the guarantee to evaluate certain
 * operators such as <tt>&amp;&amp;</tt>, <tt>||</tt> or the ternary <tt>?:</tt>
 * operator lazily
 * takes them semantically apart from all other operators and
 * methods in the otherwise strict evaluation model of java.<br>
 * It is, for instance, not possible to wrap these short-circuit operators
 * in a java method. Consider the expression: </p>
 * <pre>
 *      a == null ? "null" : a.foo()
 * </pre>
 * <p> Here, the method foo() can only be called when a is not null, but whether
 * it is called or not is decided in the <tt>?:</tt> function itself, not, as usual,
 * before the evaluation of the function starts. Therefore, the following
 * method has a completely different semantic than the operator: </p>
 * <pre>
 * static&lt;T&gt; T eitherOr(boolean cond, T t, T f) {
 *      if (cond) return t;
 *      return f;
 * }
 * </pre>
 * <p> If one would replace all occurences of the <tt>?:</tt> operator in a java program
 * with calls to the eitherOr method, the resulting program would behave
 * differently in those cases where the evaluation of the second
 * or the third argument to eitherOr would not deliver a result. <br>
 * For example, in </p>
 * <pre>
 *      eitherOr(a == null, "null", a.foo())
 * </pre>
 * <p> a NullPointerException would be thrown in the case where a was indeed null,
 * where in the original program the result was the string "null". </p>
 *
 * <p>
 * In the context of the frege language, we say that evaluation of an expression
 * <em>diverges</em> if
 * it fails to deliver a value of the expected type. This subsumes, for example,
 * endless recursion or looping, run time exceptions like division by zero and so on.
 * Divergence of an evaluation is semantically equivalent to non-termination:
 * the instance that caused the diverged evaluation will never receive an answer.
 * </p>

 * <p> Because we cannot foresee if a particular expression actually will diverge
 * or not when evaluated, we assume that (evaluation of) any expression
 * can diverge. To formalize this, we imagine every type is <em>lifted</em>
 * so that it contains an extra undefined value (sometimes called <em>bottom</em>).
 * Thus, an expression of type <tt>Int</tt> can, at runtime, evaluate to an integer
 * number or to the undefined value, one of type <tt>Bool</tt> to <tt>true</tt>,
 * <tt>false</tt> or undefined,
 * and so on. (In the frege runtime, when evaluation detects the undefined
 * value, some excpetion will be thrown.)
 * </p>

 * <p> Lazy evaluation means that expressions are evaluated at the latest possible
 * moment. This is what java does with its short-circuit operators.
 * For instance, whether the  second or third operand of the <tt>?:</tt> will be evaluated
 * depends on the boolean value of the first operand.<br>
 * A lazy language is simply one where all functions and operators
 * are "short-circuit" and any
 * expression will be evaluated only when absolutely necessary, for instance
 * when it must be printed on the users screen. </p>

 * <p> Of course, lazy evaluation does not come for free. Instead of values one
 * must pass around representations of expressions
 * that evaluate to values unless they diverge. Let us call such
 * descriptions of how to compute a value <em>lazy values</em>.
 * </p>
 * <p> Construction of lazy values costs processing time and memory.
 * The time needed in construction
 * of the lazy value is not lost, however. If the value is actually not
 * evaluated, the time that would have been spent evaluating it needlessly is,
 * so to speak, payed back.
 * </p>

 * <p> There are expression, though, where we know that they will be
 * evaluated. For example, in the frege version of the eitherOr from above: </p>
 *  <pre>
 *  eitherOr true  x y = x
 *  eitherOr false x y = y
 *  </pre>
 * <p> we know for instance that if <tt>eitherOr a b c</tt> is evaluated,
 * then <tt>a</tt> absolutely must be evaluated
 * also. This opens opportunities for optimizations that remove unneeded
 * lazyness <em>without</em> changing the behaviour of the program.</p>

 * <p> We say that a function is <em>strict</em> in (one of) its arguments
 * if it is unavoidable that the function evaluates it. This assumes that
 * the function itself is called only when their value is needed.
 * </p> <p>
 * Now, if evaluation of an expression diverges (never returns) and if that
 * expression is passed as argument to a function that is strict in that
 * argument, evaluation of that function will also diverge. </p>
 * <p>
 * Hence, it does not make a difference from a semantic point of view, whether we
 * pass a strict argument in evaluated or lazy form: If the evaluation of the argument
 * diverges, the evaluation of the function call will also diverge. If the argument
 * evaluates to a proper value, the function call may or may not diverge,
 * but if it does,
 * it will not be the fault of the argument. </p>

 * <p> Conversely, it will make a big difference whether we evaluate
 * a non strict argument before we actually call the function. This is
 * precisely the semantic difference between the java method eitherOr and
 * the java ternary operator <tt>?:</tt>  </p>
 *
 * <p> It should be clear by now, that the first argument of the <tt>?:</tt> java operator
 * is strict, while the others are non-strict.
 * </p>
 *
 * <p> The frege compiler does a so called <em>strictness analysis</em>
 * that helps it find most function arguments (and, in general)
 * pattern bound variables that are strict. </p>
 * <p>
 * In our running example the compiler would correctly infer that the
 * first argument to
 * <tt>eitherOr</tt> is strict, and the other two are non strict. When
 * code for a call to <tt>eitherOr</tt> must be generated, it will make sure
 * that expressions in the place of the second or third argument are
 * passed lazily, while the expression given for the first argument will
 * have been evaluated already to avoid the costs of creating a lazy value.
 * </p>
 * </li>
 * <li>
 * <p> Non-recursive tail calls. </p>
 * <p> If the return value of a function is expressed as a function call,
 * we call this a <em>tail call</em>. If a function tail-calls itself, this is
 * <em>tail recursion</em>. Here are examples: </p>
 * <pre>
 * even 0 = true
 * even n = odd  (n-1)
 * odd  0 = false
 * odd  n = even (n-1)
 * factorial n | n &gt;= 0 = helper 1 n where
 *    helper r 0 = r
 *    helper r n = helper (r*n) (n-1)
 * </pre>
 * <p> Function "helper" is tail recursive and will be compiled to a while loop.
 * Functions "factorial", "even" and "odd", however, employ tail calls.
 * In "factorial", this is not a problem, it just sets up parameters for the
 * very efficient "helper" function, thus we can expect a maximum stack depth of 2
 * no matter what the value of "n" is.</p>
 * <p> Things are different with "even" and "odd" that would need stack space
 * proportional to the value of their argument if implemented naively.
 * But frege functions may simply return their tail call in the form of
 * an Unknown object instead of actually evaluating it themselves. This way, a long
 * series of tail calls can be converted to a while loop, see {@link Unknown#_e}. </p>
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
 */

// The mother of all lazy values
public abstract class Unknown<V> implements Lazy<V>, java.util.concurrent.Callable<V> {
    protected volatile V result = null;        // cache the evaluated value

    /***
     * <p>Evaluates a lazy value.</p>
     *
     * <p> This is done by calling this._v() and,
          while the return value is still unevaluated,
          the _v() of the returned value and so on
          until the result is evaluated.
      </p>
     *
     * <p>The evaluated value will be a subclass of {@link Value}, such as
     * a primitive value, a {@link Boxed} java reference value or
     * even a function type. </p>
     *
     * <p>
     * e_() must be synchronized because
     * parallel threads could try to _e() us at the same time.
     * </p>
     *
     * <p> Deadlocks could occur only when one manages to
     * construct two <em>different</em> Unknown objects a and b, where
     * during evaluation of a the evaluated b is needed <b>and</b>
     * during evaluation of b the the evaluated a is needed <b>and</b>
     * one thread tries to evaluate a, thereby locking a first and
     * another thread tries to evaluate b, thereby locking b first and
     * then the first thread waits for b while the second one waits for a.</p>
     *
     * <p> This could happen, for instance,
     *  when the value of "blackhole" is computed: </p>
     *
     * <pre>
     * a = b+1
     * b = a-1
     * blackhole = case a `par` b of (a,b) -> a+b
     * </pre>
     *
     * <p> which should compile to something like this:
     *
     * <pre>
     *   static Lazy<Bint> a = new Unknown<Bint> () {
     *       Bint _v() { return b._e().j + 1; }
     *   }
     *   static Lazy<Bint> b = new Unknown<Bint> () {
     *       Bint _v() { return a._e().j - 1; }
     *   }
     *   ...
     * </pre>
     *
     * <p> This, of course, is a programming error and, ironically, no answer
     * is in some sense the correct answer to the question what number a or b is
     * exactly. </p>
     *
     * <p> In a non parallel setting (i.e., when the `par` operator degenerates
     * to the ordinary tuple constructor) the above equations  would simply
     * result in a stack overflow. From the stack trace one could easily
     * deduce that there was a blackhole, for it would look like</p>
     * <pre>
     *      called from Unknown$1._e
     *      called from Unknown$2._v
     *      called from Unknown$2._e
     *      called from Unknown$1._v
     *      called from Unknown$1._e
     *      ...
     * </pre>
     */
    public final synchronized  V  _e() {
            if (result == null) {
                Lazy<V> item = this;
                while (item._u()) item = item._v();   // looks very innocent
                result = item._e();                   // evaluated once and for all!
            }
            return result;
    }

    /**
     * <p>Do the real work in evaluating the result.</p>
     *
     * <p>From the type one can see that eval() is <b>not</b> required to return
     * the final evaluated value. It can also return another unevaluated
     * value, though the understanding is that the returned value must be
     * somehow closer to the final result, so that, by calling that values eval()
     * one gets even closer to the result and so forth until it is finally reached. </p>
     *
     * <p> This is the method subclasses of Unknown must implement. </p>
     */
    public abstract Lazy<V> _v();
    /**
     * Backwards compatibility
     */
    public Lazy<V> eval() { return this._v(); } 

    /**
     * <p> Checks if this value is unevaluated. </p>

     * <p> If this is not the case, the value is cached and can be
     * delivered via {@link Unknown#_e} immediately.</p>

     * <p> If evaluation is in progress in another thread, this method
     * will block and return <tt>false</tt> when evaluation completes.</p>
     *
     * @return <tt>true</tt> if this value is not yet evaluated, false otherwise.
     */
    final public boolean unknown() { return result == null; }
    final public boolean _u() { return result == null; }

    /**
     * <p> Implemenation of the {@link java.util.concurrent.Callable} interface </p>
     * @return the result of {@link Unknown#_e}
     */
    final public V call() { return this._e(); }
    
    @SuppressWarnings("unchecked")
    public <T extends Lazy<T>> Lazy<T> as() { return (Lazy<T>) this; }
}
