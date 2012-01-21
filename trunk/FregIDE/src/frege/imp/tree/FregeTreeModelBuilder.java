package frege.imp.tree;

import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.preferences.PreferenceValueParser.AbstractVisitor;
import org.eclipse.imp.services.base.TreeModelBuilderBase;

import frege.List.TTree;
import frege.compiler.Data.TExprT;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TPosition;
import frege.compiler.Data.TQName;
import frege.compiler.Data.TSubSt;
import frege.compiler.Data.TSymbol;
import frege.compiler.EclipseUtil;
import frege.imp.parser.FregeParseController;
import frege.prelude.Base.TList;
import frege.prelude.Base.TList.DCons;
import frege.prelude.Base.TMaybe;
import frege.prelude.Base.TTuple3;
import frege.rt.Box;

public class FregeTreeModelBuilder extends TreeModelBuilderBase {
	@Override
	public void visitTree(Object root) {
		if (root == null || !(root instanceof TGlobal))
			return;
		TGlobal global = (TGlobal) root;
		
		// fModelRoot = createTopItem(global, ModelTreeNode.DEFAULT_CATEGORY);
		
		FregeModelVisitor visitor = new FregeModelVisitor();

		// rootNode.accept(visitor);
		visitor.visit(global);
	}

	final static public int data = 0;
	final static public int link = 1;
	final static public int dcon = 2;
	final static public int clas = 3;
	final static public int inst = 4;
	final static public int func = 5;
	final static public int type = 6;
	final static public String[] categories = new String[] {
		"Data Types", "Imported Items", "Constructors", "Type Classes", "Instances", 
		"Functions and Values", "Type Aliases" 
	};
	final static public int[] order = new int[] {
		link, clas, inst, type, data, dcon, func
	};

	
	public class FregeModelVisitor /* extends AbstractVisitor */ {		
		
		public boolean visit(TGlobal g, TTree env, boolean top) {
			final TList syms = (TList) EclipseUtil.symbols(env)._e();
			// do one category after the other according to the predefined order
			for (int cat : order) {
				if (!top) { // avoid unneeded list traversals
					if (cat != func && cat != dcon) continue;
				} 
				else if (cat == dcon) continue;
				
				// go through the list of symbols and do the ones that equal the current category
				DCons elem = syms._Cons();
				boolean found = false;
				while (elem != null) {
					final TSymbol sym = (TSymbol) elem.mem1._e();
					elem = ((TList) elem.mem2._e())._Cons();
					if (sym.constructor() != cat) continue;
					if (sym.constructor() == link && TQName.M.our(TSymbol.M.alias(sym), g)) continue;
					if (top) {            // category labels at the top only before first item
						if (!found) {
							pushSubItem(new CategoryItem(categories[cat], TSymbol.M.pos(sym)));
							found = true;
						}
					}
					pushSubItem(new SymbolItem(g, sym));
					if (TSymbol.M.has$env(sym))  visit(g, TSymbol.M.env(sym), false);
					else if (TSymbol.M.has$expr(sym)) {
						final TMaybe mbex       = TSymbol.M.expr(sym);
						final TMaybe.DJust just = mbex._Just();
						if (just != null) {
							final TExprT expr = (TExprT) just.mem1._e();
							visit(g, expr);
						}
					}
					popSubItem();
				}
				if (found) popSubItem();
				found = false;
			}
			return true;
		}
		
		public boolean visit(TGlobal g, TExprT expr) {
			System.err.println("visiting: " + g.toString() + ", " + expr.toString());
			TList symbols = (TList) FregeParseController.funStG(
					frege.compiler.EclipseUtil.exprSymbols(expr), g);
			TList.DCons node = symbols._Cons();
			while (node != null) {
				TSymbol sym = (TSymbol) node.mem1._e();
				if (TSymbol.M.has$env(sym))
					this.visit(g, TSymbol.M.env(sym), false);
				node = ((TList) node.mem2._e())._Cons();
			}
			return true;
		}
		
		public boolean visit(TGlobal g) {
			final TSubSt sub = TGlobal.sub(g);
			final String pack = TSubSt.thisPack(sub).j;
			
			pushSubItem(new PackageItem(pack, TSubSt.thisPos(sub)));
			if  (! "".equals(pack)) {
				final TList pnps = (TList) EclipseUtil.imports(g)._e();
				DCons elem = pnps._Cons();
				while (elem != null) {
					final TTuple3 tuple = (TTuple3) elem.mem1._e();
					elem = ((TList) elem.mem2._e())._Cons();
					final TPosition pos = (TPosition) tuple.mem1._e();
					final String ns     = Box.<String>box(tuple.mem2._e()).j;
					final String p      = Box.<String>box(tuple.mem3._e()).j;
					createSubItem(new ImportItem(pos, ns, p));
				}
			}
			popSubItem();
			
			if  (! "".equals(pack)) 
				return visit(g, TGlobal.thisTab(g), true);
			return true;
		}
	}
}
