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
 * <p> Frege functions with arity 1. </p>
 *
 * <p> Function values are immutable and thus can be reused as often as one
 * needs them. If the frege function type involves no constraints, then
 * a single function object is sufficient for all possible instantiations
 * of the type variables, see below.</p>
 *
 */
public abstract class Fun1<X> extends frege.runtime.Lambda {
   /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Delayed} that 
     * will pass the argument to the {@link Fun1#eval} method of this function
     * when evaluated. </p>
     *
     * @return an instance of type <tt>Delayed</tt>  that promises to produce a
     * value of the return type of this function.
     */
    final public Delayed apply(final Object arg1) {
        return new Delayed () {
        	Object darg1 = arg1;
            final public Object eval() {
                return Fun1.this.eval(frege.runtime.Delayed.once(darg1, darg1 = null));
            }
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
	final public  <B, Y extends B> Fun1<B> toSuper(Fun1<Y> it) {
    	return (Fun1<B>) it;    	
    }
	
    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {@link Delayed#eval} method
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
    abstract public X eval(Object arg1);
}
