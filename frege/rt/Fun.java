package frege.rt;


// $Author$
// $Date$
// $Rev$
// $Id$

/**
 * <p> Prototype for Frege functions. </p>
 *
 * <p> All function classes derive from this one and provide implementations for
 * the single argument version of {@link Fun#a}. </p>
 *
 * <p> Because functions are themselves values,
 * we implement {@link Value} and {@link Lazy} like any other value.
 * To avoid confusion, one must make clear to oneself that an (un)evaluated function
 * value is not the same as an (un)evaluated result of a <em>function application</em>.
 * </p>
 */

public abstract class Fun<A, B> implements Value, Lazy<Fun<A,B>> {
    
    /**
     * <p>Apply this function to a possibly lazy argument.</p>
     *
     * @return a lazy value that promises to produce a
     * value of the return type of this function.
     */
    public abstract Lazy<B> a(final Lazy<A> arg);
    
    /**
     * <p> Always <tt>0</tt> for function values. </p>
     * @return 0
     */
    final public int     _c() { return 0; }          // interface Value
    
    /**
     * <p> Return this function object. </p>
     * @return <tt>this</tt>
     */
    final public Fun<A,B> _e() { return this; }       // interface Lazy
    
    /**
     * <p> Return this function object. </p>
     * @return <tt>this</tt>
     */
    final public Fun<A,B> _v() { return this; }       // interface Lazy
    
    /**
     * <p> Always <tt>false</tt> for function values. </p>
     * @return <tt>false</tt>
     */
    final public boolean _u() { return false; }      // interface Lazy
    
    @SuppressWarnings("unchecked") 
    public <a,b> Fun<a,b> coerce() {
      return (Fun<a,b>) this;
    }
}
