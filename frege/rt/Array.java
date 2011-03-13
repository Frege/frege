package frege.rt;


// $Author$
// $Date$
// $Rev$
// $Id$

/**
 * <p> Mutable array of frege values. </p>
 *
 * <p> Used to realize type Prelude.Array elem s. </p>
 *
 * <p> This is not a pure type. </p>
 */
public final class Array<T extends Lazy<T>>  implements Cloneable {
    final private T[] j;
    public final Array<T> clone()                       { return new Array<T>(j.clone()); }
    public            Array(final T[] arr)              { j = arr; }
    public            Array(final int size)             { j = Boxed.<T>arrayNew(size); }
    final public int  length()                          { return j.length; }
    final public void setAt(final int i, final T v)     { j[i] = v; }
    final public T    getAt(final int i)                { return j[i]; }
}
