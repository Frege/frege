/*
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
 
 */

package frege.runtime;

/**
 * Something that can be applied to a value.
 * 
 * <p>This interface will be implemented by 
 * {@link Delayed} and {@link Lambda} 
 * so that {@link Applicable#apply} always returns
 * another {@link Applicable}, and hence, for application of lambda bound functions,
 * the following code can be generated:
 * <p>
 * <code>f.apply(arg1).apply(arg2)</code>
 * <p>
 *  and this works no matter if f is actually an unary function
 *  that returns another function or a binary function.
 *  <p>
 *  In the latter case, the intermediate {@link Delayed} 
 *  casts the evaluated value to {@link Lambda}
 * 
 * @author ingo
 *
 */
public interface Applicable {
	/**
	 * Apply a {@link Lambda}  
	 * or evaluate a {@link Lazy} value 
	 * and apply the resulting {@link Lambda}.
	 * 
	 * @param arg  the argument
	 * @return a {@link Lazy} value or another {@link Lambda}
	 * @throws ClassCastException if invoked on a {@link Lazy} 
	 *                            value that does not evaluate to a {@link Lambda}
	 */
	public Applicable apply(Object arg);
	
	/**
	 * <p>Get the {@link Lazy} result of applications.</p>
	 * <p>When the last argument has been applied, 
	 *  the result is a {@link Lazy} object, which can be obtained
	 *  through this method.</p>
	 *  
	 *  @return the {@link Lazy} value
	 *  @throws ClassCastException if invoked on an {@link Lambda} that is not yet fully applied.
	 */
	public Lazy result();
}
