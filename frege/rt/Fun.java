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
 * <p> Prototype for Frege functions. </p>
 *
 * <p> All function classes derive from this one and provide implementations for
 * the single argument version of {@link Fun#a}. </p>
 *
 * <p> Because functions are themselves values,
 * we implement {@link Value} and {@link Lazy} like any other value.
 * To avoid confusion, one must make clear to oneself that an (un)evaluated function
 * value is not the same as an (un)evaluated result of a <em>function application</em>.
 * </p>
 */

public abstract class Fun<A, B> implements Value, Lazy<Fun<A,B>> {
    
    /**
     * <p>Apply this function to a possibly lazy argument.</p>
     *
     * @return a lazy value that promises to produce a
     * value of the return type of this function.
     */
    public abstract Lazy<B> a(final Lazy<A> arg);
    
    /**
     * <p> Always <tt>0</tt> for function values. </p>
     * @return 0
     */
    final public int     _c() { return 0; }          // interface Value
    
    /**
     * <p> Return this function object. </p>
     * @return <tt>this</tt>
     */
    final public Fun<A,B> _e() { return this; }       // interface Lazy
    
    /**
     * <p> Return this function object. </p>
     * @return <tt>this</tt>
     */
    final public Fun<A,B> _v() { return this; }       // interface Lazy
    
    /**
     * <p> Always <tt>false</tt> for function values. </p>
     * @return <tt>false</tt>
     */
    final public boolean _u() { return false; }      // interface Lazy
    
    @SuppressWarnings("unchecked") 
    public <a,b> Fun<a,b> coerce() {
      return (Fun<a,b>) this;
    }
}
