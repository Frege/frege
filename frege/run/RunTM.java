/**
 * <p> Runtime support for Frege </p>
 */

/*
    Copyright © 2011 - 2015, Ingo Wechsung
 
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
	 * <p> This is used to coerce higher ranked functions.
	 * They are passed around as {@link Object} 
	 * and are instantiated at the needed type when they're used. </p>
	 * <p> Note that Frege type checking ensures that the given type is an
	 * instance of the original polymorphic type of the function.</p>
	 * @param it 
	 * @return A function with the correct type.
	 */
	@SuppressWarnings("unchecked")
	public static<G> G higherRank(Object it) {
		return (G) it;
	}

	/**
	 * Implementation for 
	 * <code>pure native constructor frege.run.RunTM.constructor :: a -> Int</code>
	 */
	final public static int constructor(Value v) {		// if it is statically known that v is a Value 
		return v.constructor(); 
	}
	// final public static int constructor(Integer v) { return v; }
	final public static int constructor(Object v) { 	// if v is completely unknown, it could still be a Value
		if (v instanceof Value) return ((Value)v).constructor();
		if (v instanceof Short) return ((Short)v).intValue();
		if (v instanceof Integer) return ((Integer)v).intValue();
		return 0;
	}
}
