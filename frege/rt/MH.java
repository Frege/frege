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


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

// $Author$
// $Date$
// $Rev$
// $Id$

/**
 * <p> Wrapper for {@link MethodHandle} and utilities that deal with MethodHandles. </p>
 *
 * <p> A {@link MH} that holds a non satisfied {@link MethodHandle} evaluates to itself.
 * However, if all arguments are supplied evaluation will cause invocation of the
 * {@link MethodHandle}. </p> 
 */

public final class MH extends Unknown<FV> implements FV, App {
    /** <p> required to qualify as {@link FV} */
    public final int constructor() { return 0; }
    /** <p> Tells how many arguments we need before we can invoke.</p> */
    public final int arity;
    /** <p> The {@link MethodHandle}.</p> */
    public final MethodHandle j;
    
    public String toString() {
        return "MH{arity=" + arity + ", j=" + j.toString() + "}";
    }
    
    /** <p> Create a MH from a {@link MethodHandle}, takes arity from type </p> */
    public MH(final MethodHandle it) { 
        j = it; 
        arity = it.type().parameterCount();
        if (arity > 0) result = this;       // prevent evaluation
    }
    
    /** <p> Create a MH from a {@link MethodHandle} with a given arity. </p> */
    public MH(MethodHandle it, int ari) {
        j = it; 
        arity = ari;
        if (arity > 0) result = this;       // prevent evaluation
    }

    /** <p> Evaluation of a MH</p>
        <p> If the arity is not 0, it evaluates to itself. </p>
        <p> If the arity is 0, it evaluates the result of the invokation of the MethodHandle </p>
    */
    @Override public final Lazy<FV> _v() {
        // if (arity > 0) return this;
        // if (result
        try {
            return (Lazy<FV>) j.invoke/*Exact*/(); 
        } catch (Throwable e) {
            // e.printStackTrace();
            throw new Error("MethodHandle evaluation error", e);
        }
    }

    /**
        <p> Apply a MethodHandle to an argument and compute a result.</p>
        
        <p> This is used when an unknown function must be applied. For example consider</p>
        <pre>
        fold f s (x:xs) = fold f (f s x) xs
        
        g :: (Int -> Int) -> Int -> (Int -> Int)
        g h i j = (h i) + j
        
        sum = fold g (0+) [1,2,3]
        </pre>
        
        <p> But in fold, (f s) could already be a function.
        If so, we must evaluate it and only then apply the next argument.
        Therefore the common idiom is</p>
        <pre>
               // code for  f s x
               new MH(f).apply(s)._e().apply(x)
        </pre>
        
    */
    final public MH apply(Lazy<FV> v) {
        final MethodHandle r = j.bindTo(v);
        return new MH(r, arity-1);
    }

    /** <p> an often used {@link MethodType} for Lazy method(Lazy) </p> */
    final public static MethodType mtLL = MethodType.methodType(Lazy.class, Lazy.class);

    
    /**
        <p> wrapper for {@link java.lang.invoke.MethodHandles.Lookup#findStatic}</p>
        <p> Throw a {@link Error} if something goes wrong as this must never happen in a
        correct Frege program.</p>
        @return the desired method handle
    */
    final static public MethodHandle findStatic(final Class<?> in,
                                        final String name,
                                        final MethodType type) {
        try {
            return
                MethodHandles.lookup().findStatic(in, name, type);
        }
        catch (Exception e) {
            throw new Error("Can't get method handle for static method " + in.getName() + "." + name, e);
        }
    }

    /**
        <p> wrapper for {@link java.lang.invoke.MethodHandles.Lookup#findVirtual}</p>
        @return the desired method handle
    */
    final static public java.lang.invoke.MethodHandle findVirtual(final Class<?> in,
                                        final String name,
                                        final java.lang.invoke.MethodType type) {
        try {
            return 
                java.lang.invoke.MethodHandles.lookup().findVirtual(in, name, type);
        }
        catch (Exception e) {
            // System.err.println(e.getMessage());
            throw new Error("Can't get method handle for " + in.getName() + "." + name, e);
        }
    }

    /**
        <p> wrapper for {@link java.lang.invoke.MethodHandles.Lookup#findStaticGetter}</p>
        @return the desired method handle
    */
    final static public java.lang.invoke.MethodHandle  findStaticGetter(final Class<?> in,
                                        final String name,
                                        final Class<?> type) {
        try {
            return 
                java.lang.invoke.MethodHandles.lookup().findStaticGetter(in, name, type);
        }
        catch (Exception e) {
            // System.err.println(e.getMessage());
            throw new Error("Can't get method handle for static field " + in.getName() + "." + name, e);
        }
    }
    
    /**
        <p> wrapper for {@link java.lang.invoke.MethodHandles.Lookup#findGetter}</p>
        @return the desired method handle
    */
    final static public java.lang.invoke.MethodHandle  findGetter(final Class<?> in,
                                        final String name,
                                        final Class<?> type) {
        try {
            return 
                java.lang.invoke.MethodHandles.lookup().findGetter(in, name, type);
        }
        catch (Exception e) {
            // System.err.println(e.getMessage());
            throw new Error("Can't get method handle for " + in.getName() + "." + name, e);
        }
    }
    

    public final static java.lang.invoke.MethodHandle unboxObject = findGetter(Box.class, "j", Object.class);
    public final static java.lang.invoke.MethodHandle unboxRef(final Class<?> result) {
        return unboxObject.asType(java.lang.invoke.MethodType.methodType(result, Box.class));
    }
    // public final static java.lang.invoke.MethodHandle unboxFun = findGetter(Boxed.class,     "j", java.lang.invoke.MethodHandle.class);
    public final static java.lang.invoke.MethodHandle unboxInt = findGetter(Box.Int.class, "j", int.class);

}

