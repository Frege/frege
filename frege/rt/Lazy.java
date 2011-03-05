package frege.rt;

// $Author$
// $Date$
// $Rev$
// $Id$


/**
 * <p> Lazy&lt;V&gt; designates a possibly lazy value that can be evaluated
 * to give a proper value of type V.</p>
 * <p>
 * This interface is implemented by all classes that are proper frege values.
 * Hence, every x of type X can be passed or returned
 * where a Lazy&lt;X&gt; is expected,
 * but the inverse is not true.
 * </p> <p>
 * Note that a variable or argument of type Lazy&lt;{@link Value}&gt; is assignment
 * compatible with every frege value.
 * </p>
 * <p> Actual lazy values are introduced when a unary function (see {@link Fun1})
 * is applied to a value (see {@link Fun1#a}).
 * (Because application of a n-ary function to a value results in a (n-1)-ary
 * function for n&gt;1, this will happen everytime a
 * n-ary function is applied to n arguments.)
 * Such an application will never evaluate the
 * value, but rather construct an object of a subclass of {@link Unknown} that
 * knows how to evaluate the actual value by running the method that implements
 * the function. </p>
 * <p> It is suggested to subclass {@link Unknown} to make custom lazy values. In the
 * case of values whose definition is self-referential, the compiler will
 * generate code that does just that. </p>
 *
 *
 */

public interface Lazy<V>  {
    /**
     * <p>Evaluates the lazy value.</p>

     * <p>Non-lazy values evaluate to themselves by returning <tt>this</tt>.</p>
     *
     * @return the evaluated value or <tt>this</tt> if it is already
     *         evaluated.
     */
    public V       _e();     // real values return "this"

    /**
     * <p>Performs a single evaluation step.</p>
     *
     * <p> This function must be implemented in subclasses of {@link Unknown} to do
     * the real evaluation work. It is invoked by {@link Unknown#_e}, which is itself
     * final and thus cannot be overridden.</p>
     *
     * <p> Objects of classes that are not subclasses of {@link Unknown} just
     * return <tt>this</tt>.</p>
     *
     * @return another lazy value that may be a bit closer to the final result.
     */
    public Lazy<V>    _v();     // real values return "this"

    /**
     * <p> Tells if this is a yet unevaluated value. </b>
     * @return <tt>true</tt>, if this is an unevaluated instance of {@link Unknown}
     *         and <tt>false</tt> in all other cases.
     *
     * <p> This function helps to avoid instanceof and explicit type conversions.
     * It is used by {@link Unknown#_e} to decide if another invokation
     * of {@link Unknown#_v} is needed.<br>
     * Non-lazy values just return <tt>false</tt>.
     * </p>
     */
    public boolean _u();     // is this an unevaluated value?
}