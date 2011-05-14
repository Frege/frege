/**
 * Swing support for frege
 */

/*
 * $Author$
 * $Revision$
 * $Date$
 * $Id$
 */ 

package frege.rt;

public abstract class SwingSupport {
    final static Boxed.Int guiWorld = Boxed.Int.mk(1);
    public static <A extends Lazy <A>, B extends Lazy<B>>  java.lang.Runnable runnable(final Fun<A, B> arg1) {
        return new java.lang.Runnable() {
            public void run() {
                arg1.<Boxed.Int,B>coerce().a(guiWorld)._e();
            }
        };
    }
}
