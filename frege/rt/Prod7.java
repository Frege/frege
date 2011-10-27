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
 * <p> Base class for values constructed with 7-ary constructors. </p>
 *
 * <p> This will be extended by constructors of sum types and by product types.
 *  Subclasses can overwrite the {@link Value#_c} method.
 * </p>
 *
 * <p> Note that Prod<sub><em>7</em></sub> is not a subclass of Prod<sub><em>6</em></sub>! </p>
 */
public class Prod7 extends Val {
    /** <p>Field 1 </p> */
    public final Lazy<Val> mem1;
    /** <p>Field 2 </p> */
    public final Lazy<Val> mem2;
    /** <p>Field 3 </p> */
    public final Lazy<Val> mem3;
    /** <p>Field 4 </p> */
    public final Lazy<Val> mem4;
    /** <p>Field 5 </p> */
    public final Lazy<Val> mem5;
    /** <p>Field 6 </p> */
    public final Lazy<Val> mem6;
    /** <p>Field 7 </p> */
    public final Lazy<Val> mem7;
    /** <p> Constructor. </p> */
    protected Prod7(final Lazy<Val> arg1,final Lazy<Val> arg2,final Lazy<Val> arg3,final Lazy<Val> arg4,final Lazy<Val> arg5,final Lazy<Val> arg6,final Lazy<Val> arg7) {
        mem1 = arg1;
        mem2 = arg2;
        mem3 = arg3;
        mem4 = arg4;
        mem5 = arg5;
        mem6 = arg6;
        mem7 = arg7;
    }
}
