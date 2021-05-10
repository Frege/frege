/* «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»

    Copyright © 2011 - 2021, Ingo Wechsung
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
    
/*
 * $Author$
 * $Revision$
 * $Id$
 * $Date$
 */

package frege.runtime;
import java.lang.annotation.*;

/**
 * <p>Annotations for the class files produced by the frege compiler.</p>
 * <p>The compiler annotates the class that corresponds to a module 
 * with the annotations defined here.<br>
 * This makes it possible to recreate a symbol table of an imported package
 * up to the expressions for variable symbols. Used in the compiler for imports,
 * in the documentation tool, and possibly other tools.</p>
 */
public class Meta {
    /**
     * <p>General information for a frege package.</p>
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FregePackage {
        /** <p>Name of the source file.</p> */
        String      source()    default "";
        /** <p>Compile time.</p> */
        long        time()      default 0;
        /** <p>Major target version.</p> */
        int			jmajor()	default 1;
        /** <p>Minor target version.</p> */
        int			jminor()	default 7;
        /** <p>The documentation of the package.</p> */
        String      doc()       default "";
        /** <p>Name of imported packages.</p> */
        String[]    imps()      default {};
        /** <p>Namespaces of imported packages.</p> */
        String[]    nmss()      default {};
        /** <p>Table of Tau types.</p> */
        Tau[]       taus()      default {};
        /** <p>Table of Rho types.</p> */
        Rho[]       rhos()      default {};
        /** <p>Table of Sigma types.</p> */
        Sigma[]     sigmas()    default {};
        /** <p>Table of type aliases.</p> */
        SymA[]      symas()     default {};
        /** <p>Table of type classes.</p> */
        SymC[]      symcs()     default {};
        /** <p>Table of instances.</p> */
        SymI[]      symis()     default {};
        /** <p>Table of data types.</p> */
        SymT[]      symts()     default {};
        /** <p>Table of symbolic links.</p> */
        SymL[]      symls()     default {};
        /** <p>Table of values and functions.</p> */
        SymV[]      symvs()     default {};
        /** <p>Table of expressions.</p> */
        Expr[]      exprs()     default {};
    }



    /**
     * <p>The equivalent to QName.</p>
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QName {
        int     kind()      default 1;      // 0 = TName, 1 = VName, 2 = MName
        String  pack()      default "frege.Prelude";
        String  base();
        String  member()    default "";     // only set when kind=2
    }

    /**
     *  <p>The equivalent for a Tau or Kind.</p>
     *  <p>References to other Tau's and Kind's appear as indexes into the Tau table. </p>
     */
    // public enum TKind {TApp, TFun, TCon, TVar, TMeta};
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Tau {
        int     kind()      default 3;      // TApp=0, TFun=1, TCon=2, TVar=3, KApp=8, KType=9, KVar=10, KGen=11
        QName[] tcon()      default {};     // TCon
        int     suba()      default 0;      // TApp a b or TFun a b, Tau for KGen, kind for TCon and TVar Tapp
        int     subb()      default 0;      // second Tau for TApp, second Kind for KApp
        String  tvar()      default "";     // TVar
    }

    /**
     * the equivalent to a Sigma
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Sigma {
        String[]    bound()     default {};
        int[]       kinds()     default {};
        int         rho();
    }


    /**
     * the equivalent to a Rho
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Rho {
        boolean     rhofun()    default true;       // RhoTau or RhoFun
        Context[]   cont()      default {};
        int         sigma()     default 0;          // index into Sigma table
        int         rhotau()    default 0;          // index into rhofun?Rho:Tau table
    }

    /**
     * the equivalent to a Context
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Context {
        QName       clas();                         // class name
        int         tau()       default 0;          // index in Tau table
    }

    /**
     * equivalent of a type definition
     */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymA {
        int      offset()       default 0;
        QName    name();
        int[]    vars()         default {};           // tau indexes
        int      typ();                               // sigma index
        String   doc()          default "";
        boolean  publik()       default true;         // will it be imported by default?
        int		 kind()		    default 0;			  // kind index
    }

    /**
     * equivalent for a SymV
     */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymV {
    	int     offset()        default 0;
        QName   name();
        int     sig();                              // sigma index
        String  nativ()         default "";
        boolean pur()           default false;
        String  doc()           default "";
        boolean abst()          default false;      // whether abstract
        String  stri();                             // strictness encoded as string
        int     depth();                            // number of arguments, i.e. FuncN class used
        int     rkind();                            // dstawr - -00 no w-function, may return lazy
                                                    //          -01 no w-function, returns value
                                                    //          -10 worker function, may return lazy
                                                    //          -11 worker function, returns value
                                                    //          1-0 return value is lazy in all  cases
                                                    //          0-0 return value is lazy in some cases
                                                    //          
                                                    //         1--- tail recursive
                                                    //        1---- simple expression
                                                    //       1----- function can be called
                                                    //              without fear of stack overflow
        int      expr()         default 0;          // index into expression table if >0
        boolean  publik()       default true;       // will it be imported by default
        int[]	throwing()		default {};			// index of taus this function throws
        QName[] over()			default {};			// names of members this one overloads
        int		 op()			default 0;	   		// operator associativity and precedence (TokenID.ord)
        int[]	 gargs()		default {};			// generic type argument tau indexes
    }

    /**
     * equivalent for a SymL
     */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymL {
    	int     offset()       default 0;
        QName   name();
        QName   alias();
        String  doc()          default "";
        boolean  publik()      default true;   // will it be imported by default
    }

    /**
     * encoding for a field
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Field {
    	int			offset()	default 0;
    	String		name()		default "";
    	String		doc()		default "";
    	boolean		privat()	default false;	// private or public
    	boolean		strict()	default true;	// strict or lazy
    	int 		sigma();					// sigma index
    }
    /**
     * equivalent for a SymD
     */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymD {
    	int     offset()       default 0;
        QName    name();
        int      cid();                       // constructor id
        int      typ();                       // sigma index
        Field[]  fields()		default {};	  // empty if no fields at all
        String   doc()          default "";
        // String   stri();                      // strictness encoded as string
        boolean  priv()         default false; // constructor is private
        boolean  publik()       default true;  // will it be imported by default
        int		 op()			default 0;	   // operator
    }

    /*
     * equivalent for a SymC
     */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymC {
    	int     offset()       default 0;
        QName    name();
        int      tau();                       // tau index 
        QName[]  sups()         default {};   // super classes
        QName[]  ins1()         default {};   // instantiated types
        QName[]  ins2()         default {};   // instances for instantiated types
        SymL[]   lnks();                      // aliases in environment
        SymV[]   funs();                      // functions in environment
        String   doc()          default "";
        boolean  publik()       default true;   // will it be imported by default
    }

    /*
     * equivalent for a SymI
     */

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymI {
    	int     offset()       default 0;
        QName    name();
        QName    clas();
        int      typ();                       // sigma index
        SymL[]   lnks();                      // aliases in environment
        SymV[]   funs();                      // functions in environment
        String   doc()          default "";
    }

    /*
     * equivalent for a SymT
     */

    // doc::Maybe String, name::QName, typ::Sigma, env::Symtab, nativ::Maybe String}
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SymT {
    	int     offset()       default 0;
        QName    name();
        int      typ();                       // sigma index
        SymD[]   cons();                      // constructors
        SymL[]   lnks();                      // aliases in environment
        SymV[]   funs();                      // functions in environment
        String   nativ()        default "";   // java type
        String   doc()          default "";
        boolean  prod()         default false;  // whether this is a product type
        boolean  isEnum()       default false;  // whether this is a enum type
        boolean  pur()          default false;  // whether this is a pure (immutable) native type
        boolean  newt()         default false;  // whether this is a new type (1-tuple)
        boolean  publik()       default true;   // will it be imported by default
        int      kind()			default 0;		// index of kind
        int[]	 gargs()		default {};		// generic type argument tau indexes
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    /*
     * encoding of expressions, no let allowed.
     */
    public @interface Expr {
        /* 
         * expression kind
         * 0 - Ann
         * 1 - App
         * 2 - Case
         * 3 - Con
         * 4 - Ifte
         * 5 - Lam
         * 6 - Lit
         * 7 - local Vbl
         * 8 - global Vbl
         * 9 - Let
         */
        int        xkind()         default 7;     // local Vbl
        QName[]    name()          default {};    // set for global Vbl, Con
        int        lkind()         default 0;     // Lit: ord LiteralKind
                                                  // Ann: sigma index
                                                  // Case: ord CKind
        String     varval()        default "";    // Literal value
        int[]      alts()          default {};    // case alternatives or lambda
                                                  // first half are expression indexes for patterns
                                                  // second half are expression indexes for expressions
                                                  // For Let, the number of elements is a multiple of 3
                                                  // and each value bound by the let is a triple (v, s, x)
                                                  // where v is an index of an Expr that describes the local variable
                                                  //       s is a sigma index or -1 if v was not annotated
                                                  //       x is the index of an Expr for the expression defining v
        int        subx1()         default 0;     // index of 1st subexpression, set for Ann, App, If, Case and Let
        										  // or uid of local Vbl
        int        subx2()         default 0;     // index of 2nd subexpression, set for App, If
        int        subx3()         default 0;     // index of 3rd subexpression, set fot If
    }
    
    /**
     * This annotation is on the Ĳ static nested class of a module.
     * It tells which function "pointers" are implemented in this module.
     * 
     * This will avoid multiple generation of function pointers for
     * the same function in different classes, since the function 
     * pointers of imported classes can be re-used.
     * 
     * Entries in qnames() and jnames() with the same index belong together.
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FunctionPointers {
    	/** qualified names of functions */
    	QName[]		qnames()	default {};
    	/** 
    	 * names of function pointers. 
    	 * 
    	 *  This is only the member name, the package name and Ĳ 
    	 *  must be prepended upon reading!
    	 */ 
    	String[]	jnames()	default {};
    }
}
