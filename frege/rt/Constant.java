package frege.rt;

// $Author: ingo $
// $Date: 2011-02-08 20:20:43 +0100 (Di, 08 Feb 2011) $
// $Rev: 144 $
// $Id: Constant.java 144 2011-02-08 19:20:43Z ingo $

/**
 *  <p>Base class for nullary constructors.</p>
 *
 *  <p> Apart from the functionality provided by the {@link Lazy} interface,
 *  nullary consructors are just constants. The compiler will
 *  enforce that there is at most one object per constructor, though as long as
 *  object identities are not examined, this is transparent. </p>
 *
 *  <p> Every sub class must implement the {@link Value#_c} method that tells what
 *  constant (constructor) exactly this is. </p>
 */

public abstract class Constant implements Value {

    /**
     * Tell what constructor this is.
     *
     * <p> Constructor numbers are assigned by the compiler starting with 0
     * for each type in the order the constructors are declared.
     * Hence, the tuple (type, constructor number) is uniqe within
     * a frege package. Because no two types declared in different packages
     * can be equal, it is even unique in the whole universe. </p>
     *
     * @return a small natural number indicating the constructor for this constant
     */
    public abstract int _c();

    /**
     * <p> Provide default implementation of {@link Lazy#_u} for subclasses </p>
     * @return false
     */
    final public boolean _u() { return false; }
}