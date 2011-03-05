package frege.rt;


// $Author: ingo $
// $Date: 2011-03-03 23:42:07 +0100 (Do, 03 Mrz 2011) $
// $Rev: 256 $
// $Id: Ref.java 256 2011-03-03 22:42:07Z ingo $

/**
 * <p> Mutable references. </p>
 *
 * <p> Used to realize type Prelude.IORef a s. </p>
 *
 */
/**
 * <p> Boxed references. </p>
 * <p> This is not a pure type. </p>
 */
public final class Ref<T extends Lazy<T>> {
    /**
     * <p>Mutable reference to a frege object.</p>
     *
     * <p> This is used for the IORef type in the Prelude. </p>
     */
    private T j;
    /** Construct a reference. Because this is a native function, the type is correct. */
    public Ref(T a) { j = a; }
    /** getter */
    public T get() { return j; }
    /** setter */
    public void put(T a) { j = a; }
}
