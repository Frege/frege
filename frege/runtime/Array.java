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
    
package frege.runtime;


/**
 * <p> Mutable array of frege values. </p>
 *
 * <p> Used to realize type Prelude.Array elem s. </p>
 *
 * <p> This is not a pure type. </p>
 */
public final class Array  implements Cloneable {
    final private Object[] j;
    public final Array  clone()                              { return new Array(j.clone()); }
    public              Array(final Object[] arr)            { j = arr; }
    public              Array(final int size)                { j = new Object[size]; }
    final public int    length()                             { return j.length; }
    final public void   setAt(final int i, final Object v)   { j[i] = v; }
    final public Object getAt(final int i)                   { return j[i]; }
    
    /**
     * Support for int arrays
     * @author ingo
     *
     */
    public final static class Int {
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
        final public static int[] arrayNew(int size) { return  new int[size]; }

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

}
