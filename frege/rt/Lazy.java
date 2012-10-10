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
 * <p> Lazy&lt;V&gt; designates a possibly lazy value that can be evaluated
 * to give a proper value of type V.</p>
 * <p>
 * This interface is implemented by all classes that are proper frege values.
 * Hence, every x of type X can be passed or returned
 * where a Lazy&lt;X&gt; is expected,
 * but the inverse is not true.
 * </p> <p>
 * Note that a variable or argument of type Lazy&lt;{@link Value}&gt; is assignment
 * compatible with every frege value.
 * </p>
 * <p> Actual lazy values are introduced when a unary function (see {@link Lambda})
 * is applied to a value (see {@link Lambda#apply}).
 * (Because application of a n-ary function to a value results in a (n-1)-ary
 * function for n&gt;1, this will happen everytime a
 * n-ary function is applied to n arguments.)
 * Such an application will never evaluate the
 * value, but rather construct an object of a subclass of {@link Unknown} that
 * knows how to evaluate the actual value by running the method that implements
 * the function. </p>
 * <p> It is suggested to subclass {@link Unknown} to make custom lazy values. In the
 * case of values whose definition is self-referential, the compiler will
 * generate code that does just that. </p>
 *
 *
 */

public interface Lazy<V>  {
    /**
     * <p>Evaluates the lazy value.</p>

     * <p>Non-lazy values evaluate to themselves by returning <tt>this</tt>.</p>
     *
     * @return the evaluated value or <tt>this</tt> if it is already
     *         evaluated.
     */
    public V       _e();     // real values return "this"

    /**
     * <p>Performs a single evaluation step.</p>
     *
     * <p> This function must be implemented in subclasses of {@link Unknown} to do
     * the real evaluation work. It is invoked by {@link Unknown#_e}, which is itself
     * final and thus cannot be overridden.</p>
     *
     * <p> Objects of classes that are not subclasses of {@link Unknown} just
     * return <tt>this</tt>.</p>
     *
     * @return another lazy value that may be a bit closer to the final result.
     */
    public Lazy<V>    _v();     // real values return "this"

    /**
     * <p> Tells if this is a yet unevaluated value. </b>
     * @return <tt>true</tt>, if this is an unevaluated instance of {@link Unknown}
     *         and <tt>false</tt> in all other cases.
     *
     * <p> This function helps to avoid instanceof and explicit type conversions.
     * It is used by {@link Unknown#_e} to decide if another invokation
     * of {@link Unknown#_v} is needed.<br>
     * Non-lazy values just return <tt>false</tt>.
     * </p>
     */
    public boolean _u();     // is this an unevaluated value?
}