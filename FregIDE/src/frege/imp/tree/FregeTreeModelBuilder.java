package frege.imp.tree;

import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.preferences.PreferenceValueParser.AbstractVisitor;
import org.eclipse.imp.services.base.TreeModelBuilderBase;

import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TSubSt;

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
		// START_HERE
		public boolean visit(TGlobal g) {
			// System.err.println("visiting: " + g.toString());
			final TSubSt sub = TGlobal.sub(g);
			createSubItem(new PackageItem( TSubSt.thisPack(sub).j, TSubSt.thisPos(sub)));
			return true;
		}
	}
}
