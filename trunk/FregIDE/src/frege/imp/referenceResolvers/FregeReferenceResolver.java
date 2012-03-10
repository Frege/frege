package frege.imp.referenceResolvers;


import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.parser.SymbolTable;
import org.eclipse.imp.services.IReferenceResolver;

import frege.List.TTree;
import frege.compiler.BaseTypes.IShow_Token;
import frege.compiler.BaseTypes.TToken;
import frege.compiler.BaseTypes.TTokenID;
import frege.compiler.Data;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TQName;
import frege.compiler.Data.TSymbol;
import frege.imp.parser.FregeParseController;
import frege.prelude.PreludeBase.TEither;
import frege.prelude.PreludeBase.TEither.DLeft;
import frege.prelude.PreludeBase.TEither.DRight;
import frege.prelude.PreludeBase.TMaybe;
import frege.prelude.PreludeBase.TMaybe.DJust;
import frege.rt.Box;


public class FregeReferenceResolver implements IReferenceResolver {

	public static class Symbol {
		public final TGlobal g;
		public final TSymbol sym;
		public Symbol(TGlobal g, TSymbol sym) { this.g = g; this.sym = sym; }
		public String toString() {
			return Data.INice_QName.nicer(TSymbol.M.name(sym), g);
		}
	}
	
	public static class Namespace {
		public final TGlobal g;
		public final String  ns;
		public final String pack;
		public Namespace(TGlobal g, String ns, String p) { 
			this.g = g; 
			this.ns = ns;
			this.pack = p;
		}
		public String toString() {
			return "package " + pack;
		}
	}
	
	public FregeReferenceResolver() {
	}

	/**
	 * Get the text associated with the given node for use in a link
	 * from (or to) that node
	 */
	public String getLinkText(Object node) {
		// TODO Replace the following with an implementation suitable to your language and reference types
		return "unimplemented";    // node.toString();
	}

	/**
	 * Get the target for the given source node in the AST produced by the
	 * given Parse Controller.
	 */
	public Object getLinkTarget(Object node, IParseController controller) {
		// START_HERE
		// TODO Replace the following with an implementation suitable for your language and reference types
		TGlobal g = null;
		
		if (controller != null) {
			Object o = controller.getCurrentAst();
			if (o != null && o instanceof TGlobal) g = (TGlobal) o;
		}
		
		if (g != null && node != null && node instanceof TToken) {
			TToken tok = (TToken) node;
			System.err.println("getLinkTarget: " + IShow_Token.show(tok));
			final int tid = TToken.tokid(tok).j;
			if (tid != TTokenID.VARID.j 
					&& tid != TTokenID.CONID.j
					&& tid != TTokenID.QUALIFIER.j
					&& (tid < TTokenID.LOP0.j ||  tid > TTokenID.SOMEOP.j)) return null;
			final TMaybe mb = (TMaybe) TGlobal.resolved(g, tok)._e();
			final DJust just = mb._Just();
			if (just == null) return null;
			final TEither lr = (TEither) just.mem1._e();
			final DRight right = lr._Right();
			if (right != null) {
				// this is a QName
				TQName q = (TQName) right.mem1._e();
				final TMaybe mbsym = (TMaybe) TQName.M.findit(q, g)._e();
				final DJust  jsym  = mbsym._Just();
				if (jsym == null)	return null; 	// not found?
				final TSymbol sym = (TSymbol) jsym.mem1._e();
				return new Symbol(g, sym);
			}
			final DLeft  left = lr._Left();
			if (left != null) {
				// this is a namespace
				String ns = TToken.value(tok);
				final TTree tree = TGlobal.namespaces(g);
				final TMaybe mbpack = TTree.M.lookupS(tree, ns);
				final DJust jpack = mbpack._Just();
				if (jpack == null) return null;
				String pack = Box.<java.lang.String>box(jpack.mem1._e()).j;
				return new Namespace(g, ns, pack);
			}
		}

		return null;
	}
}
