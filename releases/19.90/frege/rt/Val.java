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
 * Designates an evaluated frege value or function.
 * This is the base class of all non-lazy frege values in the Java7 code generation.
 */
public class Val implements FV {
    /*
     * <p> Applies this Val to another Val. </p>
     * <p> This only makes sense when this FunVal is actually a function </p>
     
     public Lazy<Val> apply(Lazy<Val> it) {
        throw new Error("cannot apply a " + this.getClass().getName());
     }
     */
     
    /**
     * <p> 0 for most frege values, override if not so. </p>
     *
     * @return 0
     */
    public int     constructor() { return 0; }
    /**
     * <p> Always this instance. </p>
     *
     * @return <tt>this</tt>
     */
    final public Val    _e() { return this; }
    /**
     * <p> Always this instance. </p>
     *
     * @return <tt>this</tt>
     */
    final public Val    _v() { return this; }
    /**
     * <p> Always <tt>false</tt>. </p>
     *
     * @return <tt>false</tt>
     */
    final public boolean _u() { return false; }
}