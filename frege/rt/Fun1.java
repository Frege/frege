package frege.rt;

// $Author$
// $Date$
// $Rev$
// $Id$

/**
 * <p> Frege functions with arity 1. </p>
 *
 * <p> Like with {@link Product1}, {@link Product2}, ... each function type is
 * independent from each other,
 * though Fun<sub><em>i</em></sub> uses Fun<sub><em>i-1</em></sub>,
 * except Fun1 which uses {@link Unknown}. </p>
 *
 * <p> Function values are immutable and thus can be reused as often as one
 * needs them. If the frege function type involves no constraints, then
 * a single function object is sufficient for all possible instantiations
 * of the type variables, see below.</p>
 *
 * <h3> Function instantiation </h3>
 * <p> One needs to obtain a function object with a correct java compile time type
 * for any instantiation of the corresponding frege type.</p>
 * <p> For this task, every subclass of a function type shall provide
 * a static final method <b>mk</b> that is paramterized with the
 * same number of types as there are type variables in the frege type and
 * returns a function object of the appropriate java compile time type.
 * If the frege type contains no class constraints,
 * the <b>mk</b> method will have an empty parameter list
 * and it is safe
 * to return the same singleton object on each invocation.
 * In that case,
 * the only purpose of <b>mk</b> is
 * to hide the type cast that raises an unchecked warning.<br>
 * An annotation that supresses the unchecked warning is permissible
 * on the <b>mk</b>-method.</p>
 *
 * <p> If the frege type contains class constraints, then <b>mk</b> shall
 * take arguments of the appropriate interface types in the order the constraints
 * are listed in the frege type and it shall pass them to the private
 * constructor in the same order and count.
 * The constructor shall
 * store them in final instance members
 * named <tt>ctx1</tt>, <tt>ctx2</tt>, ...., <tt>ctxn</tt>. </p>
 *
 * <p> A function object instantiated with type class constraints can still be
 * reused for the function instantiated at the very same type. For example,
 * it is often convenient to use <tt>this</tt> in recursive calls.</p>
 *
 * <p>In other words, <b>mk</b> has the task to hide the constructor
 * and thus make it possible to avoid creation of unnessecary objects while
 * providing an uniform interface for obtaining a function object,
 * though this cannot be expressed in java.</p>
 *
 * <p>
 * Each function class Fun<sub><em>n</em></sub> is parameterized with n+1 type arguments
 * that give the principal types of the arguments and that of the return type.
 * For example Fun1&lt;Bint, Bbool&gt; is a function that takes an integer value
 * and computes a boolean value. However, the apply method {@link Fun1#a} as
 * well as the run method {@link Fun1#r} accept also lazy arguments and
 * the run method will produce a lazy result. </p>
 * <p> Note that, from the perspective of Java, a <tt>Fun1&lt;Ta, Fun1&lt;Tb,Tc&gt;&gt;</tt>
 * is quite different from a <tt>Fun2&lt;Ta,Tb,Tc&gt;</tt>.
 * When applied to an argument of type <tt>Ta</tt>,
 * the former will return an <tt>Unknown&lt;Fun1&lt;Tb,Tc&gt;&gt;</tt> while
 * the {@link Fun2} will return a <tt>Fun1&lt;Tb,Tc&gt;</tt>.
 * <br>Informally, the Fun1 wraps a java method that takes one argument and returns a
 * possibly lazy function object of type <tt>Fun1&lt;Tb, Tc&gt;</tt>,
 * while the Fun2 is implemented with a two argument
 * java method that returns a possibly lazy <tt>Tc</tt>.
 * <p>
 * Subclasses may provide additional (possibly static) worker functions
 * that really do the real work and take and return more specific, even primitive
 * types.
 * </p>
 *
 */
public abstract class Fun1<Ta,Tb> extends Fun<Ta,Tb> {
    /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Unknown} that
     * will pass the argument to the {@link Fun1#r} method of this function
     * when evaluated. </p>
     *
     * @return a lazy value that promises to produce a
     * value of the return type of this function.
     */
    final public Unknown<Tb> a(final Lazy<Ta> arg) {
        return new Unknown<Tb> () {
            final public Lazy<Tb> _v() { return Fun1.this.r(arg); }
        };
    }
    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {@link Unknown#_v} method
     * of the lazy value returned by {@link Fun1#a} when that lazy value
     * is evaluated. It actually performs computation and
     * returns a result or another lazy value that will evaluate to the result.<br>
     * It must be implemented by all subclasses.</p>
     * <p> Once a function is instantiated, its <tt>r</tt>-method may be
     * invoked directly. Specifically, the following identities hold:
     * <pre>
     * f.r(x)      =  f.a(x)._v()
     * f.r(x)._e() =  f.a(x)._e()
     * </pre>
     * but the expressions on the left save creation and destruction of an Unknown instance.
     * </p>
     *
     * @return boxed and possibly lazy result
     */
    abstract public Lazy<Tb> r(Lazy<Ta> arg);               // will finally call function code
    /**
     * <p> Coerce the function to another type. </p>
     * <p> I see no other way to get around the limitations of the java type system, sorry.</p>
     * <p> This will be used in the case of constructor classes, for example:</p>
     * <pre>
     * class ListSource t where
     *     toList :: t elem -> [elem]
     * instance ListSource Maybe where
     *      toList (Just a) = [a]
     *      toList Nothing  = []
     * </pre>
     * This results in:
     * <pre>
     * public interface CListSource&lt;t&gt; {
     *    public Fun1&lt;elem&gt; Fun1&lt;t, TList&lt;elem&gt;&gt; toListFun();
     * }
     * </pre>
     * It is not possible to parameterize the type parameter t in java.
     * Therefore, in the instance class:
     * <pre>
     * public class IListSource_Maybe implements CListSource&lt;TMaybe&gt; {
     *     public Fun1&lt;elem&gt; Fun1&lt;TMaybe, TList&lt;elem&gt;&gt; toListFun() {
     *          return FtoList.&lt;elem&gt;n().&lt;TMaybe, TList&lt;elem&gt;&gt;coerce();
     *     }
     * }
     * </pre>
     * we can't parameterize TMaybe either, because then the compiler complains that toListFun
     * is not implemented. This leaves us no choice but to coerce the instance function (which has
     * of course the 
     * correct type <code>Fun1&lt;TMaybe&lt;elem&gt;, TList&lt;elem&gt;&gt;</code>) 
     * to the required type.
     * Consider: we coerce deliberately to the <b>wrong</b> type
     * and this just to make the compiler happy. </p>
     * <p> To be sure, when we use the function returned by toListFun, 
     * we must coerce it back to the
     * correct type, i.e.</p>
     * <pre>
     *  toList (Just 'c')
     * </pre>
     * <p> will translate to:</p>
     * <pre>
     *  new IListSource_Maybe().&lt;Boxed.Char&gt;toListFun()                   // wrong type
     *      .&lt;TMaybe&lt;Boxed.Char&gt;, TList&lt;Boxed.Char&gt;&gt;coerce()  // corrected type
     *      .a(TMaybe.DJust.&lt;Boxed.Char&gt;mk(Boxed.Char.mk('c')))
     * </pre>
     * Note that the function type in the second coerce
     * is exactly the type we would get when we called 
     * <code>IListSource_Maybe.FtoList.&lt;Boxed.Char&gt;n()</code> directly,
     * but the latter is of course not possible when the dictionary is passed 
     * as a parameter of type CListSource.
     */
    @SuppressWarnings("unchecked") 
    final public <a,b> Fun1<a,b> coerce() {
      return (Fun1<a,b>) this;
    }
}
