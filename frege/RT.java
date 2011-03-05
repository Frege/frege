package frege;

import frege.rt.*;

/*
 * $Author$
 * $Date$
 * $Revision$
 * $Id$
 */
/**
 *  <p> Utility functions for frege code. </p>
 */
public class RT {
    /**
     * Get the constructor
     */
    public final static<V extends Lazy<V>> int constructor(V x) { return ((Value) x)._c(); }

    /**
     * <p> Copy a {@link java.util.regex.Matcher} object. </p>
     *
     * <p> Most matcher operations implemented in
     * {@link java.util.regex.Matcher} change the state of the
     * matcher instance.
     * Therefore we can't use them directly in frege code. </p>
     *
     * <p> Instead, we provide here a re-implementation of the functionality
     * we need. The idea is to make a copy of the matcher <b>before</b> the
     * destructive operation, run the operation on the copy and return it
     * to frege code. This is safe because there is no reference to the
     * copy before the operation in frege code.
     * <br>As a consequence, many matcher operations appear in frege as
     * function from Matcher to Matcher or Maybe Matcher.
     * </p>
     *
     * <p>This function relies on the fact that
     * {@link java.util.regex.Matcher#toMatchResult} effectively makes a
     * copy (yet an incomplete one) of the current matcher. It completes the copy by invoking
     * {@link java.util.regex.Matcher#usePattern}
     * which sets up the internal arrays.</p>
     *
     * <p>The copy can not be used to retrieve information about the last match
     * operation of the original matcher.</p>
     *
     *
     * @return a copy of a matcher
     */

    public static java.util.regex.Matcher clone(final java.util.regex.Matcher m) {
        return ((java.util.regex.Matcher)m.toMatchResult()).usePattern(m.pattern());
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#usePattern
     * @return the copy the operation was performed on
     */
    public static java.util.regex.Matcher usePattern(final java.util.regex.Matcher m,
                                                     final java.util.regex.Pattern p) {
        return ((java.util.regex.Matcher)m.toMatchResult()).usePattern(p);
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#useAnchoringBounds
     * @return the copy the operation was performed on
     */
    public static java.util.regex.Matcher useAnchoringBounds(final java.util.regex.Matcher m,
                                                             final boolean b) {
        final java.util.regex.Matcher c = clone(m);
        c.useAnchoringBounds(b);
        return c;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#matches
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher matches(final java.util.regex.Matcher m) {
        final java.util.regex.Matcher c = clone(m);
        if (c.matches()) return c;
        return null;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#find
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher find(final java.util.regex.Matcher m) {
        final java.util.regex.Matcher c = clone(m);
        if (c.find()) return c;
        return null;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#usePattern
     * @see java.util.regex.Matcher#find
     * @return the copy the operation was performed on, if
     * the operation was successful, otherwise <b>null</b>.
     */
    public static java.util.regex.Matcher usePatternAndFind(final java.util.regex.Matcher m,
                                                            final java.util.regex.Pattern p) {
        final java.util.regex.Matcher c = usePattern(m, p);
        final boolean emptymatch = m.start(0) == m.end(0);
        if (emptymatch)
            if (c.find(m.end(0))) return c; else return null;
        if (c.find()) return c;
        return null;
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#replaceFirst
     * @return a string
     */
    public static java.lang.String replaceFirst(final java.util.regex.Matcher m,
                                                final java.lang.String r) {
        return clone(m).replaceFirst(r);
    }
    /**
     * <p>Perform a matcher operation on a copy of the passed Matcher.</p>
     * @see java.util.regex.Matcher#replaceAll
     * @return a string
     */
    public static java.lang.String replaceAll(final java.util.regex.Matcher m,
                                                final java.lang.String r) {
        return clone(m).replaceAll(r);
    }
    
    /**
     * <p> Exception thrown when the undefined value is evaluated. </p>
     */
    public static class Undefined extends java.lang.IllegalArgumentException {
        public Undefined(String err) {
            super(err);
        }
        public Undefined(String err, Throwable cause) {
            super(err, cause);
        }
        public final Undefined die()  { throw this; }
    }
    /**
     * <p> Exception thrown when all matches of a case or lambda fail. </p> 
     */
    public static class NoMatch extends Undefined {
        public NoMatch(String qname, int line, Object x) {
            super(qname + " at line " + line + " no match for value " + x);
        }
    }
    /**
     * <p> Utility method used by String.show to quote a string. </p> 
     */
    final public static java.lang.String quoteStr(java.lang.String a) {
		java.lang.StringBuilder sr = new java.lang.StringBuilder();
		sr.append('"');
		int i = 0;
		char c;
		while (i<a.length()) {
			c = a.charAt(i++);
			if (c<' ' || c == '\177') {
				sr.append('\\');
				sr.append(java.lang.String.format("%o", (int) c));
			}
			else if (c == '\\' || c == '"') {
				sr.append('\\');
				sr.append(c);
			}
			else sr.append(c);
		}
		sr.append('"');
		return sr.toString();
	}

    /**
     * <p> Utility method used by Char.show to quote a character. </p> 
     */
	final public static java.lang.String quoteChr(char c) {
        java.lang.StringBuilder sr = new java.lang.StringBuilder();
        sr.append("'");
        if (c<' ' || c == '\177') {
            sr.append('\\');
            sr.append(java.lang.String.format("%o", (int) c));
        }
        else if (c == '\\' || c == '\'') {
            sr.append('\\');
            sr.append(c);
        }
        else sr.append(c);
        sr.append("'");
        return sr.toString();
    }
    /*      Now in frege.rt.Boxed.XXX
     * <p>Operations for arrays with primitive elements.</p>
     * <p> For each primitive type in boolean, byte, char, short, int, long, float and double
     * we have the following utility functions:
     * <pre>
     * static <i>type</i>[] arr<i>type</i>new(int size) { return new <i>type</i>[size]; }
     * static <i>type</i>   arr<i>type</i>get(<i>type</i>[] arr, int inx) { return arr[inx]; }
     * </pre>
     *
     * The compiler needs only {@link RT#arrintlen} and {@link RT#arrintget}.
     *

    public static boolean[] arrbooleannew(final int size) {return new boolean[size];}
	public static boolean   arrbooleanget(final boolean[] arr, final int inx) {return arr[inx];}
	public static boolean[] arrbooleanupd(final boolean[] arr, final int inx, final boolean upd) {
		boolean[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrbooleanset(final boolean[] arr, final int inx, final boolean set) {arr[inx] = set;}
	public static int  arrbooleanlen(final boolean[] arr) { return arr.length; }

    public static char[] arrcharnew(final int size) {return new char[size];}
	public static char   arrcharget(final char[] arr, final int inx) {return arr[inx];}
	public static char[] arrcharupd(final char[] arr, final int inx, final char upd) {
		char[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrcharset(final char[] arr, final int inx, final char set) {arr[inx] = set;}
	public static int  arrcharlen(final char[] arr) { return arr.length; }

    public static byte[] arrbytenew(final int size) {return new byte[size];}
	public static byte arrbyteget(final byte[] arr, final int inx) {return arr[inx];}
	public static byte[] arrbyteupd(final byte[] arr, final int inx, final byte upd) {
		byte[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrbyteset(final byte[] arr, final int inx, final byte set) {arr[inx] = set;}
	public static int arrbytelen(final byte[] arr) { return arr.length; }

    public static short[] arrshortnew(final int size) {return new short[size];}
	public static short   arrshortget(final short[] arr, final int inx) {return arr[inx];}
	public static short[] arrshortupd(final short[] arr, final int inx, final short upd) {
		short[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrshortset(final short[] arr, final int inx, final short set) {arr[inx] = set;}
	public static int  arrshortlen(final short[] arr) { return arr.length; }

    public static int[] arrintnew(final int size) {return new int[size];}
	public static int arrintget(final int[] arr, final int inx) {return arr[inx];}
	public static int[] arrintupd(final int[] arr, final int inx, final int upd) {
		int[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrintset(final int[] arr, final int inx, final int set) {arr[inx] = set;}
	public static int arrintlen(final int[] arr) { return arr.length; }

    public static long[] arrlongnew(final int size) {return new long[size];}
	public static long arrlongget(final long[] arr, final int inx) {return arr[inx];}
	public static long[] arrlongupd(final long[] arr, final int inx, final long upd) {
		long[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrlongset(final long[] arr, final int inx, final long set) {arr[inx] = set;}
	public static int arrlonglen(final long[] arr) { return arr.length; }

    public static float[] arrfloatnew(final int size) {return new float[size];}
	public static float   arrfloatget(final float[] arr, final int inx) {return arr[inx];}
	public static float[] arrfloatupd(final float[] arr, final int inx, final float upd) {
		float[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrfloatset(final float[] arr, final int inx, final float set) {arr[inx] = set;}
	public static int  arrfloatlen(final float[] arr) { return arr.length; }

    public static double[] arrdoublenew(final int size) {return new double[size];}
	public static double   arrdoubleget(final double[] arr, final int inx) {return arr[inx];}
	public static double[] arrdoubleupd(final double[] arr, final int inx, final double upd) {
		double[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrdoubleset(final double[] arr, final int inx, final double set) {arr[inx] = set;}
	public static int  arrdoublelen(final double[] arr) { return arr.length; }
	*/
}