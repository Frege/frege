/* «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»

    Copyright © 2011 - 2015, Ingo Wechsung
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
 * <p> Frege functions with arity 7. </p>
 *
 * <p> See {@link Fun1} for a general discussion of untyped function values. </p>
 *
 */
public abstract class Fun7<X> extends Lambda {
   /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Fun6} that collects the
     * remaining arguments and, when evaluated, invokes the {@link Fun7#eval} method of this
     * class.</p>
     *
     * @return an instance of type <tt>Fun6</tt> that waits for the
     * remaining arguments to be supplied and calls back with all arguments.
     */
    final public Fun6<X> apply(final Object arg1) {
        return new Fun6<X> () {
            final public X eval(final Object arg7,final Object arg6,final Object arg5,final Object arg4,final Object arg3,final Object arg2) {
                return Fun7.this.eval(arg7,arg6,arg5,arg4,arg3,arg2,arg1);
            }
        };
    }
    /**
     * <p>Apply this function to all its arguments at once.</p>
     *
     * <p> This method creates an instance of {@link Delayed} that,
     * when evaluated, invokes the {@link Fun7#eval} method of this
     * function.</p>
     *
     * Use of this method is preferable compared
     * to repeated invocation of the single argument form since intermediate
     * closure creation is saved.
     *
     * @return an instance of type {@link Delayed} 
     */
    final public Delayed apply(final Object arg1,final Object arg2,final Object arg3,final Object arg4,final Object arg5,final Object arg6,final Object arg7) {
        return new Delayed () {
            final public X eval() { return Fun7.this.eval(arg7,arg6,arg5,arg4,arg3,arg2,arg1); }
        };
    }
    
	/**
     * <p>Force the Java typechecker to accept sensible substitutions.</p>
     * <p>The Java typechecker won't accept a function that promises
     * to return X in place of a function that promises to return {@link Object},
     * though this should be completely save, shouldn't it?</p>
     * <p>It also refuses to accept any frege type, when {@link Lazy} is demanded,
     * though every frege type implements {@link Lazy}.</p>   
     * 
     * @return This function, now promising to return a broader type
     */
	@SuppressWarnings("unchecked")
	final public  <B, Y extends B> Fun7<B> toSuper(Fun7<Y> it) {
    	return (Fun7<B>) it;    	
    }

    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {@link Fun6#eval} method
     * of the object resulting from <tt>this.apply(...)</tt>.
     * It actually performs computation and
     * returns a result.</p>
	 * 
     * <p>Functions always return the announced type (which can be a lazy).</p>
	 *
     * <p>This method must be implemented by all subclasses.</p>
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
     * @return result
     */
    abstract public X eval(final Object arg7,final Object arg6,final Object arg5,final Object arg4,final Object arg3,final Object arg2, final Object arg1);
}
