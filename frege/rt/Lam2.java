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
 * <p> Frege lambdas with arity 2. </p>
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
public abstract class Lam2 implements Lambda {
    /**
     * <p>Apply this function to an argument.</p>
     *
     * <p> This method creates an instance of {@link Lam1}. </p>
     *
     * @return a lazy value that promises to produce a
     * value of the return type of this function.
     */
    final public Lam1 apply(final Lazy<FV> arg1) {
        return new Lam1 () {
            final public Lazy<FV> eval(final Lazy<FV> arg2) { return Lam2.this.eval(arg2, arg1); }
        };
    }
    
    /**
     * <p>Apply this function to all its arguments at once.</p>
     *
     * <p> This method creates an instance of {@link Unknown} that,
     * when evaluated, invokes the {@link Lam2#eval} method of this
     * function.</p>
     *
     * Use of this method is preferrable if all arguments are known compared
     * to repeated invokation of the single argument form since intermediate
     * closure creation is saved.
     *
     * @return an instance of type <tt>Unknown&lt;FV&gt;</tt>
     */
    final public Unknown<FV> apply(final Lazy<FV> arg1,final Lazy<FV> arg2) {
        return new Unknown<FV> () {
            final public Lazy<FV> _v() { return Lam2.this.eval(arg2,arg1); }
        };
    }
    
    /**
     * <p> Run the function. </p>
     *
     * <p> This method will be called by the {@link Lam1#eval} method,
     * which passes the arguments up in reverse order.
     * </p>
     *
     * @return boxed and possibly lazy result
     */
    abstract public Lazy<FV> eval(Lazy<FV> arg2, Lazy<FV> arg1);

    /** <p> required to qualify as {@link FV} */
    public final int      constructor() { return 0; }
    /** <p> a {@link Lam1} cannot be reduced further. </p> */
    public final boolean  _u() { return false; }
    /** <p> a {@link Lam1} evaluates to itself. </p> */
    public final Lazy<FV> _v() { return this; }
    /** <p> a {@link Lam1} evaluates to itself. </p> */
    public final FV       _e() { return this; }
    
    static Lam2 test = new Lam2() {
        public Box.Int eval(final Lazy<FV> arg2, final Lazy<FV> arg1) { return Box.Int.mk(((Box.Int)arg1._e()).j + ((Box.Int)arg1._e()).j); }
    };        
}
