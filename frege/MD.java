/*
 * $Author: ingo $
 * $Revision: 254 $
 * $Id: MD.java 254 2011-03-03 21:54:29Z ingo $
 * $Date: 2011-03-03 22:54:29 +0100 (Do, 03 Mrz 2011) $
 */

package frege;
import java.lang.annotation.*;

/**
 * <p>Annotations for the frege compiler.</p>
 * <p>The compiler annotates the main class with the annotations defined here.
 * This makes it possible to recreate a symbol table of an imported package
 * up to the expressions for variable symbols.</p>
 */
public class MD {
    /*
     * <p> Package documentation. </p>
     * <p>The package documentation is
     * taken from the documentation comment(s) preceding the
     * <b>package</b> keyword.</p>
     **
    @Retention(RetentionPolicy.RUNTIME)
	public @interface PackageDoc {
	    String      value()       default "";
	}
	 */

	/**
	 * <p>General information for a frege package.</p>
	 */
    @Retention(RetentionPolicy.RUNTIME)
	public @interface FregePackage {
	    /** <p>Name of the source file.</p> */
	    String      source()    default "";
	    /** <p>Compile time.</p> */
	    long        time()      default 0;
	    /** <p>The documentation of the package.</p> */
	    String      doc()       default "";
	    /** <p>Operator table.</p> */
	    Operator[]  ops()       default {};
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
	}

    /**
     * <p>Information from the infix* definitions.</p>
     */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Operator {        // infix 10 `==`
	    /** <p>Operator name.</p> */
		public String	name();         // "=="
		/** <p>How the operator associates (0=left, 1=right, 2=none).</p> */
		public int  	kind();         // 0=Left, 1=Right, 2=None
		/** <p>Precedence 1 to 16.</p> */
		public int		prec();         // 10
	}


    /**
     * <p>The equivalent to QName.</p>
     */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface QName {
	    int     kind()      default 1;      // 0 = TName, 1 = VName, 2 = MName
		String	pack()      default "frege.Prelude";
		String  base();
		String	member()    default "";     // only set when kind=2
	}

	/**
	 *  <p>The equivalent for a Tau.</p>
	 *  <p>References to other Tau's appear as indexes into the Tau table. </p>
	 */
	// public enum TKind {TApp, TFun, TCon, TVar, TMeta};
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Tau {
		int 	kind()      default 3;      // TApp=0, TFun=1, TCon=2, TVar=3
		QName[] tcon()      default {};     // TCon
		int     suba()      default 0;      // TApp a b or TFun a b
		int     subb()      default 0;
		String	tvar()  	default "";     // TVar
	}

    /*
     * the equivalent to a Sigma
     */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Sigma {
		String[]	bound()	    default {};
		int			rho();
	}


    /*
     * the equivalent to a Rho
     */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Rho {
		boolean		rhofun()	default true;       // RhoTau or RhoFun
		Context[]   cont()      default {};
		int 		sigma()		default 0;          // index into Sigma table
		int  		rhotau()	default 0;          // index into rhofun?Rho:Tau table
	}

	/*
	 * the equivalent to a Context
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Context {
	    QName       clas();                         // class name
	    int         tau()       default 0;          // index in Tau table
	}

    /*
     * equivalent of a type definition
     */

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymA {
	    // int     pos()     default 0;
		QName	name();
		int[]	vars()    default {};               // tau indexes
		int 	typ();                              // sigma index
		String  doc()     default "";
	}

    /*
     * equivalent for a SymV
     */

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymV {
	    // int     pos()     default 0;
		QName	name();
		int 	sig();                              // sigma index
		String  nativ()         default "";
		boolean pur()           default false;
		String  doc()           default "";
		boolean abst()          default false;      // whether abstract
		String  stri();                             // strictness encoded as string
		int     depth();                            // number of arguments, i.e. FunN class used
		int     rkind();                            // wwr - 000 no w-function, r returns lazy
		                                            //       001 no w-function, r returns boxed
		                                            //       01r w function returns lazy
		                                            //       10r w function returns boxed
		                                            //       11r w function returns unboxed
	}

	/*
     * equivalent for a SymL
     */

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymL {
	    // int     pos()     default 0;
		QName	name();
		QName   alias();
		String  doc()           default "";
	}

	/*
     * equivalent for a SymD
     */

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymD {
	    // int     pos()     default 0;
		QName	 name();
		int      cid();                      // constructor id
		int      typ();                      // sigma index
		String[] fnms()         default {};   // field names (null if no field names at all)
		int[]    ftys()         default {};   // field types
		String   doc()          default "";
		String   stri();                     // strictness encoded as string
	}

	/*
     * equivalent for a SymC
     */

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymC {
	    // int     pos()     default 0;
		QName	 name();
		int      tau();                       // tau index
		int      vkind()        default 0;    // how many type arguments are applied to class variable 
		QName[]  sups()         default {};   // super classes
		QName[]  ins1()         default {};   // instantiated types
		QName[]  ins2()         default {};   // instances for instantiated types
		SymL[]   lnks();                      // aliases in environment
		SymV[]   funs();                      // functions in environment
		String   doc()          default "";
	}

    /*
     * equivalent for a SymI
     */

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymI {
	    // int     pos()     default 0;
		QName	 name();
		QName	 clas();
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
	    // int     pos()     default 0;
		QName	 name();
		int      typ();                       // sigma index
		SymD[]   cons();                      // constructors
		SymL[]   lnks();                      // aliases in environment
		SymV[]   funs();                      // functions in environment
		String   nativ()        default "";   // java type
		// int[]    ntargs()       default {};   // type arguments for the native type (tau indexes)
		String   doc()          default "";
		boolean  prod()         default false;  // whether this is a product type
		boolean  isEnum()       default false;  // whether this is a enum type
		boolean  pur()          default false;  // whether this is a pure (immutable) native type
		boolean  newt()         default false;  // whether this is a new type (1-tuple)
	}
}