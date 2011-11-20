package frege.imp.parser;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import frege.imp.parser.FregeParseController.ASTNode;
import frege.imp.parser.FregeParseController.AbstractVisitor;

import org.eclipse.imp.parser.ISourcePositionLocator;

/**
 * NOTE:  This version of the ISourcePositionLocator is for use when the Source
 * Position Locator and corresponding Parse Controller are generated separately from
 * a corresponding set of LPG grammar templates and possibly in the absence
 * of the lexer, parser, and AST-related types that would be generated from
 * those templates.  To enable compilation of the Locator and Controller,
 * dummy types have been defined as member types of the Controller in place
 * of possibly missing lexer, parser, and AST-related types.  This version
 * of the Node Locator refers to some of those types.  When those types
 * are replaced by real implementation types, the Locator must be modified
 * to refer to those.  Apart from statements to import needed types from
 * the Parse Controller, this SourcePositionLocator is the same as that used
 * with LPG.
 * @see the corresponding ParseController type
 * 
 * @author Stan Sutton (suttons@us.ibm.com)
 * @since May 15, 2007
 */
public class FregeSourcePositionLocator implements ISourcePositionLocator {
	private final Object[] fNode = new Object[1];

	private int fStartOffset;
	private int fEndOffset;

	public FregeSourcePositionLocator() {
	}

	private final class NodeVisitor extends AbstractVisitor {

		public void unimplementedVisitor(String s) {
			// System.out.println("NodeVisitor.unimplementedVisitor:  Unimplemented");
		}

		public boolean preVisit(ASTNode element) {
			int nodeStartOffset = element.getLeftIToken().getStartOffset();
			int nodeEndOffset = element.getRightIToken().getEndOffset();
			//System.out.println("FregeNodeLocator.NodeVisitor.preVisit(ASTNode):  Examining " + element.getClass().getName() +
			//    " @ [" + nodeStartOffset + "->" + nodeEndOffset + ']');

			// If this node contains the span of interest then record it
			if (nodeStartOffset <= fStartOffset && nodeEndOffset >= fEndOffset) {
				//System.out.println("FregeNodeLocator.NodeVisitor.preVisit(ASTNode) SELECTED for offsets [" + fStartOffset + ".." + fEndOffset + "]");
				fNode[0] = element;
				return true; // to continue visiting here?
			}
			return false; // to stop visiting here?
		}
	}

	private NodeVisitor fVisitor = new NodeVisitor();

	public Object findNode(Object ast, int offset) {
		return findNode(ast, offset, offset);
	}

	public Object findNode(Object ast, int startOffset, int endOffset) {
		// System.out.println("Looking for node spanning offsets " + startOffset + " => " + endOffset);
		fStartOffset = startOffset;
		fEndOffset = endOffset;
		// The following could be treated as an IASTNodeToken, but ASTNode
		// is required for the visit/preVisit method, and there's no reason
		// to use both of those types
		((ASTNode) ast).accept(fVisitor);
		if (fNode[0] == null) {
			//System.out.println("Selected node:  null");
		} else {
			//System.out.println("Selected node: " + fNode[0] + " [" +
			//   fNode[0].getLeftIToken().getStartOffset() + ".." + fNode[0].getLeftIToken().getEndOffset() + "]");
		}
		return fNode[0];
	}

	public int getStartOffset(Object node) {
		ASTNode n = (ASTNode) node;
		return n.getLeftIToken().getStartOffset();
	}

	public int getEndOffset(Object node) {
		ASTNode n = (ASTNode) node;
		return n.getRightIToken().getEndOffset();
	}

	public int getLength(Object node) {
		ASTNode n = (ASTNode) node;
		return getEndOffset(n) - getStartOffset(n);
	}

	public IPath getPath(Object node) {
		// TODO Determine path of compilation unit containing this node
		return new Path("");
	}
}
