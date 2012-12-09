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
 * <p> Base class for values constructed with 20-ary constructors. </p>
 *
 * <p> This will be extended by constructors of sum types and by product types.
 *  Subclasses can overwrite the {@link FV#constructor} method.
 * </p>
 *
 * <p> Note that Prod<sub><em>20</em></sub> is not a subclass of Prod<sub><em>19</em></sub>! </p>
 */
public class Prod20 extends Val {
    /** <p>Field 1 </p> */
    public final Lazy<FV> mem1;
    public final static Lazy<FV> get1(Lazy<FV> p) {
        return ((Prod20) p._e()).mem1;
    }
    public final static Lambda mhget1 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem1;
        }
    };
    /** <p>Field 2 </p> */
    public final Lazy<FV> mem2;
    public final static Lazy<FV> get2(Lazy<FV> p) {
        return ((Prod20) p._e()).mem2;
    }
    public final static Lambda mhget2 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem2;
        }
    };
    /** <p>Field 3 </p> */
    public final Lazy<FV> mem3;
    public final static Lazy<FV> get3(Lazy<FV> p) {
        return ((Prod20) p._e()).mem3;
    }
    public final static Lambda mhget3 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem3;
        }
    };
    /** <p>Field 4 </p> */
    public final Lazy<FV> mem4;
    public final static Lazy<FV> get4(Lazy<FV> p) {
        return ((Prod20) p._e()).mem4;
    }
    public final static Lambda mhget4 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem4;
        }
    };
    /** <p>Field 5 </p> */
    public final Lazy<FV> mem5;
    public final static Lazy<FV> get5(Lazy<FV> p) {
        return ((Prod20) p._e()).mem5;
    }
    public final static Lambda mhget5 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem5;
        }
    };
    /** <p>Field 6 </p> */
    public final Lazy<FV> mem6;
    public final static Lazy<FV> get6(Lazy<FV> p) {
        return ((Prod20) p._e()).mem6;
    }
    public final static Lambda mhget6 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem6;
        }
    };
    /** <p>Field 7 </p> */
    public final Lazy<FV> mem7;
    public final static Lazy<FV> get7(Lazy<FV> p) {
        return ((Prod20) p._e()).mem7;
    }
    public final static Lambda mhget7 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem7;
        }
    };
    /** <p>Field 8 </p> */
    public final Lazy<FV> mem8;
    public final static Lazy<FV> get8(Lazy<FV> p) {
        return ((Prod20) p._e()).mem8;
    }
    public final static Lambda mhget8 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem8;
        }
    };
    /** <p>Field 9 </p> */
    public final Lazy<FV> mem9;
    public final static Lazy<FV> get9(Lazy<FV> p) {
        return ((Prod20) p._e()).mem9;
    }
    public final static Lambda mhget9 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem9;
        }
    };
    /** <p>Field 10 </p> */
    public final Lazy<FV> mem10;
    public final static Lazy<FV> get10(Lazy<FV> p) {
        return ((Prod20) p._e()).mem10;
    }
    public final static Lambda mhget10 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem10;
        }
    };
    /** <p>Field 11 </p> */
    public final Lazy<FV> mem11;
    public final static Lazy<FV> get11(Lazy<FV> p) {
        return ((Prod20) p._e()).mem11;
    }
    public final static Lambda mhget11 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem11;
        }
    };
    /** <p>Field 12 </p> */
    public final Lazy<FV> mem12;
    public final static Lazy<FV> get12(Lazy<FV> p) {
        return ((Prod20) p._e()).mem12;
    }
    public final static Lambda mhget12 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem12;
        }
    };
    /** <p>Field 13 </p> */
    public final Lazy<FV> mem13;
    public final static Lazy<FV> get13(Lazy<FV> p) {
        return ((Prod20) p._e()).mem13;
    }
    public final static Lambda mhget13 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem13;
        }
    };
    /** <p>Field 14 </p> */
    public final Lazy<FV> mem14;
    public final static Lazy<FV> get14(Lazy<FV> p) {
        return ((Prod20) p._e()).mem14;
    }
    public final static Lambda mhget14 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem14;
        }
    };
    /** <p>Field 15 </p> */
    public final Lazy<FV> mem15;
    public final static Lazy<FV> get15(Lazy<FV> p) {
        return ((Prod20) p._e()).mem15;
    }
    public final static Lambda mhget15 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem15;
        }
    };
    /** <p>Field 16 </p> */
    public final Lazy<FV> mem16;
    public final static Lazy<FV> get16(Lazy<FV> p) {
        return ((Prod20) p._e()).mem16;
    }
    public final static Lambda mhget16 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem16;
        }
    };
    /** <p>Field 17 </p> */
    public final Lazy<FV> mem17;
    public final static Lazy<FV> get17(Lazy<FV> p) {
        return ((Prod20) p._e()).mem17;
    }
    public final static Lambda mhget17 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem17;
        }
    };
    /** <p>Field 18 </p> */
    public final Lazy<FV> mem18;
    public final static Lazy<FV> get18(Lazy<FV> p) {
        return ((Prod20) p._e()).mem18;
    }
    public final static Lambda mhget18 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem18;
        }
    };
    /** <p>Field 19 </p> */
    public final Lazy<FV> mem19;
    public final static Lazy<FV> get19(Lazy<FV> p) {
        return ((Prod20) p._e()).mem19;
    }
    public final static Lambda mhget19 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem19;
        }
    };
    /** <p>Field 20 </p> */
    public final Lazy<FV> mem20;
    public final static Lazy<FV> get20(Lazy<FV> p) {
        return ((Prod20) p._e()).mem20;
    }
    public final static Lambda mhget20 = new Lam1 () {
        public final Lazy<FV> eval(Lazy<FV> p) {
            return ((Prod20) p._e()).mem20;
        }
    };
    /** <p> Constructor. </p> */
    protected Prod20(final Lazy<FV> arg1,final Lazy<FV> arg2,final Lazy<FV> arg3,final Lazy<FV> arg4,final Lazy<FV> arg5,final Lazy<FV> arg6,final Lazy<FV> arg7,final Lazy<FV> arg8,final Lazy<FV> arg9,final Lazy<FV> arg10,final Lazy<FV> arg11,final Lazy<FV> arg12,final Lazy<FV> arg13,final Lazy<FV> arg14,final Lazy<FV> arg15,final Lazy<FV> arg16,final Lazy<FV> arg17,final Lazy<FV> arg18,final Lazy<FV> arg19,final Lazy<FV> arg20) {
        mem1 = arg1;
        mem2 = arg2;
        mem3 = arg3;
        mem4 = arg4;
        mem5 = arg5;
        mem6 = arg6;
        mem7 = arg7;
        mem8 = arg8;
        mem9 = arg9;
        mem10 = arg10;
        mem11 = arg11;
        mem12 = arg12;
        mem13 = arg13;
        mem14 = arg14;
        mem15 = arg15;
        mem16 = arg16;
        mem17 = arg17;
        mem18 = arg18;
        mem19 = arg19;
        mem20 = arg20;
    }
}
