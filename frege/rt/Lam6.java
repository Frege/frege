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
 * <p> Frege lambdas with arity 6. </p>
 *
 * <p> See {@link Lam1} for a general discussion of untyped function values. </p>
 *
 */
public abstract class Lam6 implements App {
   /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Lam5} that collects the
     * remaining arguments and, when evaluated, invokes the {@link Lam6#eval} method of this
     * class.</p>
     *
     * @return an instance of type <tt>Lam5</tt> that waits for the
     * remaining arguments to be supplied and calls back with all arguments.
     */
    final public Lam5 apply(final Lazy<FV> arg1) {
        return new Lam5 () {
            final public Lazy<FV> eval(final Lazy<FV> arg6,final Lazy<FV> arg5,final Lazy<FV> arg4,final Lazy<FV> arg3,final Lazy<FV> arg2) {
                return Lam6.this.eval(arg6,arg5,arg4,arg3,arg2,arg1);
            }
        };
    }
    /**
     * <p>Apply this function to all its arguments at once.</p>
     *
     * <p> This method creates an instance of {@link Unknown} that,
     * when evaluated, invokes the {@link Lam6#eval} method of this
     * function.</p>
     *
     * Use of this method is preferrable if all arguments are known compared
     * to repeated invokation of the single argument form since intermediate
     * closure creation is saved.
     *
     * @return an instance of type <tt>Unknown&lt;T7&gt;</tt>
     */
    final public Unknown<FV> apply(final Lazy<FV> arg1,final Lazy<FV> arg2,final Lazy<FV> arg3,final Lazy<FV> arg4,final Lazy<FV> arg5,final Lazy<FV> arg6) {
        return new Unknown<FV> () {
            final public Lazy<FV> _v() { return Lam6.this.eval(arg6,arg5,arg4,arg3,arg2,arg1); }
        };
    }
    /*
     * <p> Always <tt>0</tt> for function values. </p>
     * @return 0
     */
    final public int     constructor() { return 0; }          // interface FV
    /*
     * <p> Return this function object. </p>
     * @return <tt>this</tt>
     */
    final public FV _e() { return this; }                    // interface Lazy<FV>
    /*
     * <p> Return this function object. </p>
     * @return <tt>this</tt>
     */
    final public Lazy<FV> _v() { return this; }              // interface Lazy
    /*
     * <p> Always <tt>false</tt> for function values. </p>
     * @return <tt>false</tt>
     */
    final public boolean _u() { return false; }              // interface Lazy
    /**
     * <p> Run the function. </p>
     *
     * <p> The run method will be called by the {@link Lam5#eval} method
     * of the lambda object resulting from <tt>this.apply(...)</tt>.
     * It actually performs computation and
     * returns a result or another lazy value that will evaluate to the result.<br>
     * This method must be implemented by all subclasses.</p>
     *
     * <p>
     * Note that the arguments must be passed in reverse order. The reason is that
     * in this way the byte code for any intermediate closure will only have to
     * push its argument and invoke the next higher closure's <tt>eval</tt> method.
     * A reordering of the arguments on the stack will not be needed. This could save
     * a substantial amounts of memory writes (I hope).
     * </p>
     *
     *
     * @return boxed and possibly lazy result
     */
    abstract public Lazy<FV> eval(final Lazy<FV> arg6,final Lazy<FV> arg5,final Lazy<FV> arg4,final Lazy<FV> arg3,final Lazy<FV> arg2, Lazy<FV> arg1);
}
