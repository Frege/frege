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
 * <p> Frege lambdas with arity 1. </p>
 *
 * <p> Like with {@link Prod1}, {@link Prod2}, ... each lambda type is
 * independent from each other,
 * though Lam<sub><em>i</em></sub> uses Lam<sub><em>i-1</em></sub>,
 * except Lam1 which uses {@link Unknown}. </p>
 *
 * <p> Function values are immutable and thus can be reused as often as one
 * needs them. If the frege function type involves no constraints, then
 * a single function object is sufficient for all possible instantiations
 * of the type variables, see below.</p>
 *
 */
public abstract class Lam1 implements Lambda {
    /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Unknown} that
     * will pass the argument to the {@link Lam1#eval} method of this function
     * when evaluated. </p>
     *
     * @return a lazy value that promises to produce a
     * value of the return type of this function.
     */
    final public Unknown<FV> apply(final Lazy<FV> arg) {
        return new Unknown<FV> () {
            final public Lazy<FV> _v() { return Lam1.this.eval(arg); }
        };
    }
    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {@link Unknown#_v} method
     * of the lazy value returned by {@link Lam1#apply} when that lazy value
     * is evaluated. It actually performs computation and
     * returns a result or another lazy value that will evaluate to the result.<br>
     * It must be implemented by all subclasses.</p>
     * <p> Once a function is instantiated, its <tt>work</tt>-method may be
     * invoked directly. Specifically, the following identities hold:
     * <pre>
     * f.eval(x)      =  f.a(x)._v()
     * f.eval(x)._e() =  f.a(x)._e()
     * </pre>
     * but the expressions on the left save creation and destruction of an Unknown instance.
     * </p>
     *
     * @return boxed and possibly lazy result
     */
    abstract public Lazy<FV> eval(Lazy<FV> arg);               // will finally call function code

    /** <p> required to qualify as {@link FV} */
    public final int      constructor() { return 0; }
    /** <p> a {@link Lam1} cannot be reduced further. </p> */
    public final boolean  _u() { return false; }
    /** <p> a {@link Lam1} evaluates to itself. </p> */
    public final Lazy<FV> _v() { return this; }
    /** <p> a {@link Lam1} evaluates to itself. </p> */
    public final FV       _e() { return this; }
    
    static Lam1 test = new Lam1() {
        public Box.Int eval(final Lazy<FV> arg) { return Box.Int.mk(((Box.Int)arg._e()).j + 1); }
    };
        
}
