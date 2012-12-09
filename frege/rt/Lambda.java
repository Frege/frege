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
 * <p> A thing that can be applied to something. </p> 
 */

public abstract class Lambda implements FV {
    /**
        <p> Apply this to an argument. </p>
    */
    public abstract Lazy<FV> apply(final Lazy<FV> v);
    /**
        <p> Apply this to an argument and cast the evaluated result to a Lambda. </p>
        <p> This is useful to avoid code like </p>
        <pre> (Lambda) (((Lambda) f.apply(x)._e()).apply(y)._e()) </pre>
        <p> Rather, we just write </p>
        <pre> f.app(x).app(y) </pre>
    */
    public final Lambda app(final Lazy<FV> v) { return (Lambda) this.apply(v)._e(); }
    
    /** <p> {@link Lambda#constructor} returns 0 for all function values </p> */
    public final int      constructor() { return 0; }
    /** <p> Return <code>false</code> because a lambda is a value.</p> */
    public final boolean  _u() { return false; }
    /** <p> A {@link Lambda} evaluates to itself. </p> */
    public final Lazy<FV> _v() { return this; }
    /** <p> A {@link Lambda} evaluates to itself. </p> */
    public final FV       _e() { return this; }
}

