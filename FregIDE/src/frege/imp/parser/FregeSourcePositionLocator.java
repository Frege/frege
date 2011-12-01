package frege.imp.parser;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import org.eclipse.imp.parser.ISourcePositionLocator;

import frege.compiler.Data.TToken;

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

	public Object findNode(Object ast, int offset) {
		return findNode(ast, offset, offset);
	}

	public Object findNode(Object ast, int startOffset, int endOffset) {
		System.out.println("Looking for node spanning offsets " + startOffset + " => " + endOffset);
		if (fNode[0] == null) {
			//System.out.println("Selected node:  null");
		} else {
			//System.out.println("Selected node: " + fNode[0] + " [" +
			//   fNode[0].getLeftIToken().getStartOffset() + ".." + fNode[0].getLeftIToken().getEndOffset() + "]");
		}
		return fNode[0];
	}

	public int getStartOffset(Object node) {
		if (node != null && node instanceof TToken) {
			return TToken.offset((TToken)node);
		}
		return 0;
	}

	public int getEndOffset(Object node) {
		if (node != null && node instanceof TToken) {
			return TToken.offset((TToken)node) + TToken.length((TToken) node) - 1;
		}
		return 0;
	}

	public int getLength(Object node) {
		if (node != null && node instanceof TToken) {
			return TToken.length((TToken)node);
		}
		return 0;
	}

	public IPath getPath(Object node) {
		// TODO Determine path of compilation unit containing this node
		return new Path("");
	}
}
