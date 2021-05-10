/**
 * <p> Runtime support for Frege </p>
 */

/*
    Copyright © 2011 - 2021, Ingo Wechsung
 
    All rights reserved.
 
    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:

    -   Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.

    -   Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution. 
        
    -   Neither the name of the copyright holder
        nor the names of its contributors may be used to endorse or
        promote products derived from this software without specific
        prior written permission.
 
    *THIS SOFTWARE IS PROVIDED BY THE
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
    THE POSSIBILITY OF SUCH DAMAGE.*
 */

package frege.run;

import frege.runtime.Value;

/**
 * @author ingo
 *
 */
public class RunTM {

	/**
	 * <p> This is used to correct the compile time type for higher ranked functions 
	 * or types instantiated with higher kinded arguments.</p>
	 * Higher ranked functions are passed around with {@link Object} in place of type variables, 
	 * and are instantiated at the needed type when they're used. </p>
	 * <p> The operations of type classes with higher kinded type variables (i.e. Functor, Monad, etc.)
	 * give rise to higher kinded types. The higher kinded  type applications are modeled in Java
	 * with one of the classes from {@link Kind}. For example, a value with type</p>
	 * <pre> [Maybe Char]</pre>
	 * is normally encoded as
	 * <pre> TList&lt;TMaybe &lt;Character&gt;&gt; </pre>
	 * <p>but when it is passed to some polymorphic function that expects at this place</p>
	 * <pre>[f e]</pre>
	 * <p>its Java type needs to be /p>
	 * <pre> TList&ltKind.U&lt;TMaybe&lt;?&gt;, Character&gt;&gt;</pre>
	 * <p>to comply. Likewise, such kinded types need to be converted back to normal ones.
	 * But the Java compiler can only understand type conversions at the outermost level 
	 * from normal to kinded (all suitable Frege types extend the possible Kind classes depending on the 
	 * kind of their type constructor). There is simply no way to tell Java that the normal
	 * notation and the kinded notation describe the very same type. For example, it cannot
	 * understand that</p>
	 * <pre>Func.U&lt;Kind.U&lt;TMaybe&lt;?&gt;, Character&gt;, Boolean&gt;</pre>
	 * <p>is the same function type as</p>
	 * <pre>Func.U&lt;TMaybe&lt;Character&gt;, Boolean&gt;</pre>
	 * <p> Note that Frege type checking ensures that the given type is an
	 * instance of the original polymorphic type of the function,
	 * so the conversion is not really "unsafe".</p>
	 * @param it 
	 * @return The value with the required compile time type. Hopefully, the JIT takes notice and eliminates this stuff.
	 */
	@SuppressWarnings("unchecked")
	public static<G> G cast(Object it) {
		return (G) it;
	}

	/**
	 * Implementation for 
	 * <code>pure native constructor frege.run.RunTM.constructor :: a -> Int</code>
	 */
	final public static int constructor(Value v) {		// if it is statically known that v is a Value 
		return v.constructor(); 
	}
	final public static int constructor(short it) { return it; }
	final public static int constructor(int it) { return it; }
	// final public static int constructor(Integer v) { return v; }
	final public static int constructor(Object v) { 	// if v is completely unknown, it could still be a Value
		if (v instanceof Value) return ((Value)v).constructor();
		if (v instanceof Short) return ((Short)v).intValue();
		if (v instanceof Integer) return ((Integer)v).intValue();
		return 0;
	}
	
	/** 
	 * <p> Command line arguments </p>
	 * 
	 * <p>When a Frege program is run by evaluating <b>main</b>, the command line
	 * arguments will be saved here and can be retrieved with
	 * the 'getArgs' function from System.Environment.
	 * 
	 * Note that when Frege code is invoked from elsewhere, the command line
	 * arguments will be the empty array, unless reset through different means.
	 * </p>
	 */
	public static String[] argv = new String[0];
}
