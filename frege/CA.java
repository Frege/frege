/*
 * $Revision$
 * $Header: E:/iwcvs/fc3/frege/CA.java,v 3.2 2010/02/18 12:31:19 iw Exp $
 * $Log: CA.java,v $
 * Revision 3.2  2010/02/18 12:31:19  iw
 * Add an attribute for the names of constructed types.
 *
 * Revision 3.1  2009/04/22 21:43:35  iw
 * compiler3
 *
 * Revision 2.8  2008/05/29 21:18:04  iw
 * - unification of Tree k v and [(k,v)] in one class
 *
 * Revision 2.7  2008/05/29 07:39:16  iw
 * - type aliases are now retained until unification
 * - type synonym declarations can occur in arbitrary order
 * - Prelude made nicer
 *
 * Revision 2.6  2008/05/21 07:30:34  iw
 * -Type must accomodate qname and Classdef must accomodate types in 'dvars'
 *
 * Revision 2.5  2008/05/19 22:51:44  iw
 * - implemented dependent types, !type is now a syntax error
 * - made Symbols.fr almost warning - free
 *
 * Revision 2.4  2008/05/18 08:41:31  iw
 * - isSet indicates also unconditional array get
 *
 * Revision 2.3  2008/04/28 16:28:12  iw
 * - provisions for documentation
 *
 * Revision 2.2  2007/09/29 17:56:56  iw
 * - kinded class variables
 *
 * Revision 2.1  2007/09/23 12:12:43  iw
 * - initila revision for self hosting derived from frege-2-prefix
 *
 * Revision 1.5  2007/09/22 14:34:29  iw
 * - moved to frege/CA.java
 *
 * Revision 1.4  2007/06/19 20:15:08  iw
 * - strictness analysis is working basically
 *
 * Revision 1.3  2007/06/17 13:42:03  iw
 * - support for changes in symbol format (arity, strct)
 * - support for inclusion of let definitions
 *
 * Revision 1.2  2006/12/26 15:41:12  iw
 * - import&export completed
 * - run javac
 *
 * Revision 1.1  2006/12/20 13:44:19  iw
 * - new file for compiler annotations
 *
 */

package frege;
import java.lang.annotation.*;

/*
 * This class conatins the definitions of the annotations.
 */
public class CA {
    /*
     * the package documentation is stored here
     * It is taken from the documentation comment(s) preceding the
     * @package@ keyword.
     */
    @Retention(RetentionPolicy.RUNTIME)
	public @interface PackageDoc {
	    String          value();
	}

	public enum Ifxkind { Left, Right, None };
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Operator {
		public String	name();
		public Ifxkind	kind();
		public int		prec();
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Operators {
		Operator[]	value();
	}

    /*
     * Imports contains package names, Namespaces namespace names
     * Values with equal index belong together.
     */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Imports {
		String[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Namespaces {
		String[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Tyname {
		String	pack() default "frege.Prelude";
		String	name();
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Qname {
		String	pack() default "frege.Prelude";
		String  typ()  default "";
		String	name();
	}

	public enum Tkind {TCon, TVar, TApp, TFun, TAli};
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Type {
		Tkind	kind();
		String	varname()	default "";
		Tyname[] constr()	default {};
		String[] fields()	default {};
		Type[]	types()		default {};
		// Tyname	con()		default @Tyname(name="->");
		// Qname[] qname()     default {};
		// boolean nativ()		default false;
		// boolean strict()	default false;
		// String	nativtype() default "";
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Sigma {
		String[]	forall()	default {};
		Rho			rho();
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Rho {
		boolean		rhofun()	default false;
		Sigma[]		sigma()		default {};
		Rho[]		rho()		default {};
		Type[]		tau()		default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Typedefs {
		Typedef[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Typedef {
	    int     pos()     default 0;
		Tyname	con();
		Type[]	vars() default {};
		Sigma	def();
		String[]    docu()  default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Classdefs {
		Classdef[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Instdefs {
		Inst[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Fundefs {
		Fundef[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Datadefs {
		Data[]	value() default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Classdef {
		int			pos()	default 0;
		Visibility	vis()	default Visibility.Public;
		Tyname		canon();
		Type		clvar();
		Type[]      dvars() default {};
		int         kind()  default 0;
		Fundef[]	meths()	default {};
		String[]    docu()  default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Inst {
		int			pos()	default 0;
		Tyname		canon();
		Tyname		clas();
		Sigma		typ();
		Fundef[]	meths() default {};
		String[]    docu()  default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Fundef {
		int			pos()	default 0;
		Visibility	vis()	default Visibility.Public;
		Qname		canon();
		Sigma		typ();
		Expr[]		expr()  default {};		// Maybe Expr
		String		nativ() default "";
		int         arity() default 0;
		boolean     strict() default false;
		boolean[]   strsig() default {};
		String[]    docu()  default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Condef {
		int			pos()	default 0;
		Visibility	vis()	default Visibility.Public;
		Qname		canon();
		Sigma		typ();
		String[]    names() default {};
		String[]    docu()  default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Expr {
		boolean		isVbl()		default	false;
		boolean		isCon()		default false;
		boolean		isLit()		default false;
		boolean		isInv()		default false;
		boolean		isApp()		default false;
		boolean		isIf()		default false;
		boolean		isCase()	default false;
		boolean		isLet()		default false;      // lets are not allowed (yet)
		boolean		isLam()		default false;
		boolean		isTyped()	default false;
		// int			pos()		default 0;		// position for varcon
		Qname[]		varcon()	default {};			// also for con
		Literal[]	lit()		default {};
		Invoke[]	inv()		default {};
		/*
		 * for (x.inv) this will be [x] and inv will contain [inv]
		 * for (fun arg1 ... argn) this contains [fun, arg1, ..., argn]
		 * for (let ... in x) this contains [x]
		 * for (if c then t else e) this contains [c,t,e]
		 * for (case x of p1 -> x1, ..., pn -> xn) this contains [x, x1, ..., xn]
		 * for (lam p x) this will be [x]
		 * for (x::t) this will be [x]
		 */
		Expr[]		subex()		default {};
		/*
		 * This will be set only when isLam() or isCase()
		 */
		Pattern[]	subpat()	default {};
		boolean		isWhen()	default false;		// Casekind==CaseWhen
		Type[]		typ()		default {};			// set iff isTyped
		Fundef[]    subdef()    default {};         // set iff isLet
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Pattern {
		boolean		isVar()		default false;
		boolean		isCon()		default false;
		boolean		isLit()		default false;
		boolean		isAt()		default false;
		boolean		isAnn()		default false;
		boolean		isMat()		default false;
		boolean		isApp()		default false;
		String		name()		default "";		// set for PVar, PAt, PMat
		String[]	fields()	default {};		// set for PVar if it has fields
		Qname[]		con()		default {};		// set for PCon
		Literal[]	lit()		default {};		// set for PLit, PMat
		Type[]		typ()		default {};		// set for PAnn
		Pattern[]	subpat()	default {};		// set for PVar with fields, PAt, PAnn, PApp
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Invoke {
		boolean		isMemfun()	default false;
		boolean		isRecinv()	default false;
		boolean		isArrinv()	default false;
		boolean		isSet()		default false; // not just Update, but set or Arrget!
		String		field()		default "";
		Expr[]		exprs()		default {};
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Literal {
		Lit			kind()		default Lit.Int;
		String		value()		default "";
	}
	public enum Lit { Int, Long, Char, String, Float, Double, Bool, Regex };
	public enum Visibility { Public, Protected, Private, Abstract };

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Data {
		int			pos()	default 0;
		Visibility	vis()	default Visibility.Public;
		Tyname		canon();
		Sigma		typ();
		Condef[]	cons()	default {};
		Fundef[]	meths()	default {};
		String[]	nativ()	default {};
		Sigma[]		base()	default {};
		Tyname[]	insts() default {};
		String[]    docu()  default {};
	}



}