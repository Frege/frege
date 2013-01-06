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
 * <p> Frege functions with arity 3. </p>
 *
 * <p> See {@link Func1} for a general discussion of untyped function values. </p>
 *
 */
public abstract class Func3 extends Lambda {
   /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Func2} that collects the
     * remaining arguments and, when evaluated, invokes the {@link Func3#eval} method of this
     * class.</p>
     *
     * @return an instance of type <tt>Func2</tt> that waits for the
     * remaining arguments to be supplied and calls back with all arguments.
     */
    final public Func2 apply(final Object arg1) {
        return new Func2 () {
            final public Object eval(final Object arg3,final Object arg2) {
                return Func3.this.eval(arg3,arg2,arg1);
            }
        };
    }
    /**
     * <p>Apply this function to all its arguments at once.</p>
     *
     * <p> This method creates an instance of {@link Delayed} that,
     * when evaluated, invokes the {@link Func3#eval} method of this
     * function.</p>
     *
     * Use of this method is preferrable compared
     * to repeated invokation of the single argument form since intermediate
     * closure creation is saved.
     *
     * @return an instance of type {@link Delayed} 
     */
    final public Delayed apply(final Object arg1,final Object arg2,final Object arg3) {
        return new Delayed () {
            final public Object eval() { return Func3.this.eval(arg3,arg2,arg1); }
        };
    }
    
    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {@link Func2#eval} method
     * of the object resulting from <tt>this.apply(...)</tt>.
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
     * @return possibly lazy result
     */
    abstract public Object eval(final Object arg3,final Object arg2, final Object arg1);
}
