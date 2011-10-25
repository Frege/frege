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
 * <p> Container for arbitrary java reference values. </p>
 *
 * <p> Used by the compiler to encode native reference types. </p>
 *
 * <p> A declaration </p>
 *
 * <pre>
 * data X = native "some.reference.type" where
 *      // member function declarations ...
 * </pre>
 *
 * <p> creates a noninstantiable class TX that serves as namepsace
 * for the member functions. Yet, the java type for frege type X
 * is always Box&lt;some.reference.type&gt;  </p>
 *
 * <p> In addition, for each primitive type supported by frege, there is
 * a nested class <tt>Box.</tt><em>type</em> where <em>type</em> is the
 * name given that type in frege.
 * Hence, {@link Box.Int} is a boxed primitive <tt><b>int</b></tt>.
 */
public class Box<T>  extends Val {
    /**
     * <p>Reference to the java object.</p>
     *
     * <p> This is used in calls to native methods, which could be member
     * functions of the type <tt>T</tt>. </p>

     * <p> Frege assumes that this field is never null. Null values
     * could be introduced by native functions whose return type
     * is incorrectly specified or by array accesses. </p>
     */
    public final T j;
    protected Box(T a) { j = a; }
    /**
     * public constructor for boxed values
     *
     * @param ref a non null reference of type <tt>T</tt>
     *
     * @return an instance of Box&lt;T&gt;.
     */
    final public static<T> Box<T> mk(T ref) { return new Box<T>(ref); }
    

    public String toString() { return j.toString(); }

    /**
     * <p> Create an array with T elements. </p>
     * <p> This works so long as we never try to cast the array implicitely or explicitely
     * to its supposed type. Therefore, native arry types like StringArray need their
     * own special array creation routines.</p>
     * @param size the size of the array
     */
    @SuppressWarnings("unchecked")
    final public static<T extends Lazy<T>> T[] arrayNew(int size) {
        return  (T[]) (new Lazy[size]);
    }

    /**
     * <p> Update array nondestructively. </p>
     * @param arr the array
     * @param i   index into arr
     * @param v   new value to set at index i
     * @return a new array that looks like the old one except that there is value v at index i
     */
    final public static<T> T[] arrayUpd(T[] arr, int i, T v) {
            T[] r = arr.clone();
            r[i] = v;
            return r;
    }
    /**
     * <p> Update array destructively. This method is <b>not</b> pure! </p>
     * @param arr the array
     * @param i   index into arr
     * @param v   new value to set at index i
     *
     * <p>Changes the passed array, therefore it is not pure. Because the return type
     * is <code>void</code> there is no way to make the frege compiler believe it is pure.</p>
     */
    final public static<T> void arraySet(T[] arr, int i, T v) { arr[i] = v; }

    /**
     * <p> Get array element. </p>
     * @param arr the array
     * @param i   index into arr
     * @return the value at index i, which may be <code>null</code>
     */
    final public static<T> T arrayGet(T[] arr, int i) { return arr[i]; }

    /**
     * <p> Get array length. </p>
     * @param arr the array
     * @return <code>arr.length</code>
     */
    final public static<T> int arrayLen(T[] arr) { return arr.length; }


    /**
     * <p> Box primitive boolean </p>
     */
    public final static class Bool extends Val {
        private Bool(final boolean c) { j = c; }
        /** the primitive boolean value boxed by this instance */
        final public boolean   j;
        /** boxed false value */
        final public static Bool f = new Bool(false);
        /** boxed true value */
        final public static Bool t = new Bool(true);
        /**
         * box a boolean value
         *
         * @return one of {@link Bool#f} or {@link Bool#t}
         */
        final public static Bool mk(boolean b) { return b?t:f; }

        public String toString() { return j?"true":"false"; }
    }


    /**
     * <p> Box primitive char </p>
     */
    public final static class Char extends Val {
        private Char(char c) { j = c; }
        /** a cache for all ASCII chars */
        private final static Char[] ascii = new Char[128];
        static { for (char c=0; c < 128; c++) ascii[c] = new Char(c); }
        /** the primitive char value boxed by this instance */
        final public char   j;
        /**
         * <p> box a char value </p>
         *
         * @return the boxed char
         */
        final public static Char mk(char c) {
            if (c >= 0 && c < 128) return ascii[c];
            return new Char(c);
        }
        public String toString() { return "'" + j + "'"; }
    }

    /**
     * <p> Box primitive int </p>
     */
    public final static class Int extends Val {
        private Int(int c) { j = c; }
        /** a cache for small ints */
        private final static Int[] small = new Int[256];
        static { for (int i=0; i < 256; i++) small[i] = new Int(i); }
        /** the primitive int value boxed by this instance */
        final public int   j;
        /**
         * <p> box an int value </p>
         *
         * @return the boxed int
         */
        final public static Int mk(int c) {
            if (c >= 0 && c < 256) return small[c];
            return new Int(c);
        }
        /** <p>The int value. This is so that one can imagine that Int is
         * declared as <code> data Int = 0 | 1 | 2 ... | -maxint | ... | -1</p>
         * @return 0
         */
        final public int       _c() { return j; }

        public String toString() { return "" + j; }

        /**
         * <p> Access array element. </p>
         * @param arr the int array
         * @param i   the index
         * @return <code>arr[i]</code>
         */
        public static final int arrayGet(int[] arr, int i) { return arr[i]; }
        /**
         * <p> Get array length. </p>
         * @param arr the int array
         * @return <code>arr.length</code>
         */
        public static final int arrayLen(int[] arr) { return arr.length; }
        /**
         * <p> Create an array of type int[]. </p>
         * @param size the size of the array
         */
        @SuppressWarnings("unchecked")
        final public static int[] arrayNew(int size) { return  new int[size]; }

        /**
         * <p> Update array nondestructively. </p>
         * @param arr the array
         * @param i   index into arr
         * @param v   new value to set at index i
         * @return a new array that looks like the old one except that there is value v at index i
         */
        final public static int[] arrayUpd(int[] arr, int i, int v) {
                int[] r = arr.clone();
                r[i] = v;
                return r;
        }
        /**
         * <p> Update array destructively. This method is <b>not</b> pure! </p>
         * @param arr the array
         * @param i   index into arr
         * @param v   new value to set at index i
         *
         * <p>Changes the passed array, therefore it is not pure. Because the return type
         * is <code>void</code> there is no way to make the frege compiler believe it is pure.</p>
         */
        final public static void arraySet(int[] arr, int i, int v) { arr[i] = v; }
    }

    /**
     * <p> Box primitive long </p>
     */
    public final static class Long extends Val {
        private Long(long c) { j = c; }
        /** a cache for small longs */
        private final static Long[] small = new Long[256];
        static { for (long i=0L; i < 256L; i++) small[(int)i] = new Long(i); }
        /** the primitive value boxed by this instance */
        final public long   j;
        /**
         * <p> box  long value </p>
         *
         * @return the boxed long
         */
        final public static Long mk(long c) {
            if (c >= 0L && c < 256L) return small[(int)c];
            return new Long(c);
        }

        public String toString() { return j + "L"; }
    }

    /**
     * <p> Box primitive float </p>
     */
    public final static class Float extends Val {
        private Float(float c) { j = c; }

        /** the primitive value boxed by this instance */
        final public float   j;
        /**
         * <p> box an float value </p>
         *
         * @return the boxed float
         */
        final public static Float mk(float c) {
            return new Float(c);
        }
        
        public String toString() { return j + "f"; }
    }

    /**
     * <p> Box primitive double </p>
     */
    public final static class Double extends Val {
        private Double(double c) { j = c; }

        /** the primitive value boxed by this instance */
        final public double   j;
        /**
         * <p> box a double value </p>
         *
         * @return the boxed double
         */
        final public static Double mk(double c) {
            return new Double(c);
        }

        public String toString() { return j + ""; }
    }
}