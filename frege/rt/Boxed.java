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
 * is always Boxed&lt;some.reference.type&gt;  </p>
 *
 * <p> In addition, for each primitive type supported by frege, there is
 * a nested class <tt>Boxed.</tt><em>type</em> where <em>type</em> is the
 * name given that type in frege.
 * Hence, {@link Boxed.Int} is a boxed primitive <tt><b>int</b></tt>.
 */
public final class Boxed<T>  implements Value, Lazy<Boxed<T>> {
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
    private Boxed(T a) { j = a; }
    /**
     * public constructor for boxed values
     *
     * @param ref a non null reference of type <tt>T</tt>
     *
     * @return an instance of Boxed&lt;T&gt;.
     */
    final public static<T> Boxed<T> mk(T ref) { return new Boxed<T>(ref); }
    /**
     * This is needed in cases where the type argument has itself type arguments
     * that must be corrected because java does not understand higher kinded type variables.
     */
    @SuppressWarnings("unchecked") 
    public <N> Boxed<N> coerce() { return (Boxed<N>) this; }
    /**
     * <p> Always 0 for boxed java values. </p>
     *
     * @return 0
     */
    final public int     _c() { return 0; }
    /**
     * <p> Always this instance. </p>
     *
     * @return <tt>this</tt>
     */
    final public Boxed<T>    _e() { return this; }
    /**
     * <p> Always this instance. </p>
     *
     * @return <tt>this</tt>
     */
    final public Boxed<T>    _v() { return this; }
    /**
     * <p> Always <tt>false</tt>. </p>
     *
     * @return <tt>false</tt>
     */
    final public boolean _u() { return false; }
    
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
     * <p> Boxed primitive boolean </p>
     */
    public final static class Bool implements Value, Lazy<Bool> {
        private Bool(boolean c) { j = c; }
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
        /** <p>Always 0</p>
         * @return 0
         */
        final public int       _c() { return 0; }
        /** <p>Always <tt>this</tt>.</p>  @return <tt>this</tt> */
        final public Bool      _e() { return this; }
        /** <p>Always <tt>this</tt>.</p>  @return <tt>this</tt> */
        final public Bool      _v() { return this; }
        /** <p>Always <tt>false</tt>.</p> @return <tt>false</tt> */
        final public boolean   _u() { return false; }
        
        public String toString() { return j?"true":"false"; }
    }


    /**
     * <p> Boxed primitive char </p>
     */
    public final static class Char implements Value, Lazy<Char> {
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
        /** <p>Always 0</p>
         * @return 0
         */
        final public int       _c() { return 0; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Char      _e() { return this; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Char      _v() { return this; }
        /** <p>Always <tt>false</tt>.</p> @return <tt>false</tt> */
        final public boolean   _u() { return false; }
        
        public String toString() { return "'" + j + "'"; }
    }

    /**
     * <p> Boxed primitive int </p>
     */
    public final static class Int implements Value, Lazy<Int> {
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
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Int      _e() { return this; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Int      _v() { return this; }
        /** <p>Always <tt>false</tt>.</p> @return <tt>false</tt> */
        final public boolean   _u() { return false; }
        
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
     * <p> Boxed primitive long </p>
     */
    public final static class Long implements Value, Lazy<Long> {
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
        /** <p>Always 0</p>
         * @return 0
         */
        final public int       _c() { return 0; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Long      _e() { return this; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Long      _v() { return this; }
        /** <p>Always <tt>false</tt>.</p> @return <tt>false</tt> */
        final public boolean   _u() { return false; }
        
        public String toString() { return j + "L"; }
    }

    /**
     * <p> Boxed primitive float </p>
     */
    public final static class Float implements Value, Lazy<Float> {
        private Float(float c) { j = c; }

        /** the primitive value boxed by this instance */
        final public float   j;
        /**
         * <p> box an int value </p>
         *
         * @return the boxed int
         */
        final public static Float mk(float c) {
            return new Float(c);
        }
        /** <p>Always 0</p>
         * @return 0
         */
        final public int       _c() { return 0; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Float      _e() { return this; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Float      _v() { return this; }
        /** <p>Always <tt>false</tt>.</p> @return <tt>false</tt> */
        final public boolean   _u() { return false; }
        
        public String toString() { return j + "f"; }
    }

    /**
     * <p> Boxed primitive double </p>
     */
    public final static class Double implements Value, Lazy<Double> {
        private Double(double c) { j = c; }

        /** the primitive value boxed by this instance */
        final public double   j;
        /**
         * <p> box an int value </p>
         *
         * @return the boxed int
         */
        final public static Double mk(double c) {
            return new Double(c);
        }
        /** <p>Always 0</p>
         * @return 0
         */
        final public int       _c() { return 0; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Double      _e() { return this; }
        /** <p>Always <tt>this</tt>.</p> @return <tt>this</tt> */
        final public Double      _v() { return this; }
        /** <p>Always <tt>false</tt>.</p> @return <tt>false</tt> */
        final public boolean   _u() { return false; }
        
        public String toString() { return j + ""; }
    }
}