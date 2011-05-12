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
    public static <T extends Lazy<T>>  java.lang.Runnable runnable(final Fun<Boxed.Int, T> arg1) {
        return new java.lang.Runnable() {
            public void run() {
                arg1.a(guiWorld)._e();
            }
        };
    }
}
