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
    /** <p> create a Runnable from a ST action </p> */
    public static <A extends Lazy <A>, B extends Lazy<B>>  java.lang.Runnable runnable(final Fun<A, B> arg1) {
        return new java.lang.Runnable() {
            public void run() {
                arg1.<Boxed.Int,B>coerce().a(guiWorld)._e();
            }
        };
    }
    /** <p> create an ActionListener from a ActionListenerT s -&gt; ST s () function </p> */
    public static <A extends Lazy <A>, B extends Lazy<B>>  
        java.awt.event.ActionListener actionListener(final Fun<Boxed<java.awt.event.ActionEvent>, Fun<A,B>> arg1) {
            return new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    arg1.a(Boxed.<java.awt.event.ActionEvent>mk(e))
                        ._e()
                        .<Boxed.Int,B>coerce()
                        .a(guiWorld)
                        ._e();
                }
            };
    }
}
