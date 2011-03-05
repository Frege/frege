package frege.rt;
// $Author$
// $Date$
// $Rev$
// $Id$
/**
 * <p> Base class for values constructed with 1-ary constructors. </p>
 *
 * <p> This will be extended by constructors of sum types and by product types.
 *  Subclasses must implement the {@link Value#_c} method and the
 * {@link Lazy} interface.
 * </p>
 *
 * <p> Note that Product<sub><em>1</em></sub> is not a subclass of Product<sub><em>0</em></sub>! </p>
 */
public abstract class Product1<T1> implements Value {
    /** <p> Must be implemented by subclasses to return their constructor number. </p> */
    public abstract int _c();
    /** <p> Default implementation of the {@link Lazy#_u} method. </p>
     *  @return false
     */
    final public boolean _u() { return false; }
    /** <p>Field 1 </p> */
    public final Lazy<T1> m1;
    /** <p> Frege function to get field 1 lazily. </p> */
    public final static class Get1<T1, T extends Product1<T1>>
            extends Fun1<T, T1> {
        public final Lazy<T1> r(final Lazy<T> arg1) {
            return arg1._e().m1;
        }
        private final static Get1 single = new Get1();
        @SuppressWarnings("unchecked")
        public final static <T1, T extends Product1<T1>>
            Get1<T1,T> n() {
                return (Get1<T1,T>) single;
        }
    }
    /** <p> Constructor. </p> */
    protected Product1(final Lazy<T1> arg1) {
        m1 = arg1;
    }
}
