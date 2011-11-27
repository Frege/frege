package frege.imp.tokenColorer;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import frege.compiler.Data.TToken;
import frege.compiler.Data.TTokenID;
import frege.compiler.Data.IShow_Token;



public class FregeTokenColorer extends TokenColorerBase implements ITokenColorer {
	protected final TextAttribute docuAttribute, conidAttribute, identifierAttribute,
			commentAttribute, specialAttribute, opAttribute,
			keywordAttribute, literalAttribute, errorAttribute;

	//  protected final TextAttribute commentAttribute, stringAttribute;

	public FregeTokenColorer() {
		super();
		// TODO Define text attributes for the various token types that will have their text colored
		//
		// NOTE: Colors (i.e., instances of org.eclipse.swt.graphics.Color) are system resources
		// and are limited in number.  THEREFORE, it is good practice to reuse existing system Colors
		// or to allocate a fixed set of new Colors and reuse those.  If new Colors are instantiated
		// beyond the bounds of your system capacity then your Eclipse invocation may cease to function
		// properly or at all.
		Display display = Display.getDefault();
		docuAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.ITALIC);
		commentAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.NORMAL);
		conidAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.NORMAL);
		identifierAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		keywordAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
		literalAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_CYAN), null, SWT.NORMAL);
		opAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.NORMAL);
		errorAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_RED), null, SWT.BOLD);
		specialAttribute = keywordAttribute;
		
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		final TToken token = (TToken) o;
		final int tid = TToken.tokid(token).j;
		
		if (tid >= TTokenID.PACKAGE.j && tid <= TTokenID.INFIXR.j) 	return keywordAttribute;
		if (tid == TTokenID.DOCUMENTATION.j)						return docuAttribute;
		if (tid == TTokenID.COMMENT.j)								return commentAttribute;
		if (tid == TTokenID.CONID.j)								return conidAttribute;
		if (tid == TTokenID.VARID.j)								return identifierAttribute;
		if (tid >= TTokenID.INTCONST.j && tid < TTokenID.REGEXP.j) 	return literalAttribute;
		if (tid == TTokenID.REGEXP.j) {
			// System.out.println("coloring ..." + IShow_Token.show(token) + " " + TToken.length(token));
			return literalAttribute;
		}
		if (tid == TTokenID.LEXERROR.j) 							return errorAttribute;

		if (tid >= TTokenID.DCOLON.j && tid <= TTokenID.EARROW.j)	return specialAttribute;
		if (tid >= TTokenID.LOP0.j && tid <= TTokenID.SOMEOP.j) {
			// System.out.println("coloring ..." + IShow_Token.show(token) + " " + TToken.length(token));
			if (TToken.value(token).length() 
					!= TToken.length(token))						return opAttribute;
			else													return identifierAttribute;
		}
		if (tid == TTokenID.CHAR.j) switch (TToken.value(token)) {
		case "_": return keywordAttribute;
		case "=": return keywordAttribute;
		case "|": return keywordAttribute;
		case ";": return identifierAttribute;
		case "(": return identifierAttribute;
		case ")": return identifierAttribute;
		case "[": return identifierAttribute;
		case "]": return identifierAttribute;
		case "{": return identifierAttribute;
		case "}": return identifierAttribute;
		default:
			System.out.println("How to colour " + IShow_Token.show(token) + " ?");
		}
		
		
		return super.getColoring(controller, token);
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}
}
