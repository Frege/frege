package frege.imp.tree;

import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.preferences.PreferenceValueParser.AbstractVisitor;
import org.eclipse.imp.services.base.TreeModelBuilderBase;

import frege.List.TTree;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TPosition;
import frege.compiler.Data.TSubSt;
import frege.compiler.EclipseUtil;
import frege.prelude.Base.TList;
import frege.prelude.Base.TList.DCons;
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

	public class FregeModelVisitor /* extends AbstractVisitor */ {
		/*
		@Override
		public void unimplementedVisitor(String s) {
		}

		public boolean visit(block n) {
			pushSubItem(n);
			return true;
		}

		public void endVisit(block n) {
			popSubItem();
		}

		public boolean visit(declarationStmt0 n) {
			createSubItem(n);
			return true;
		}

		public boolean visit(declarationStmt1 n) {
			createSubItem(n);
			return true;
		}

		public boolean visit(assignmentStmt n) {
			createSubItem(n);
			return true;
		}
		*/
		
		public boolean visit(TGlobal g) {
			// System.err.println("visiting: " + g.toString());
			final TSubSt sub = TGlobal.sub(g);
			pushSubItem(new PackageItem( TSubSt.thisPack(sub).j, TSubSt.thisPos(sub)));
			{
				final TList pnps = (TList) EclipseUtil.imports(g)._e();
				DCons elem = pnps._Cons();
				while (elem != null) {
					final TTuple3 tuple = (TTuple3) elem.mem1._e();
					elem = ((TList) elem.mem2._e())._Cons();
					final TPosition pos = (TPosition) tuple.mem1._e();
					final String ns     = Box.<String>box(tuple.mem2._e()).j;
					final String pack   = Box.<String>box(tuple.mem3._e()).j;
					createSubItem(new ImportItem(pos, ns, pack));
				}
			}
			popSubItem();
			return true;
		}
	}
}
