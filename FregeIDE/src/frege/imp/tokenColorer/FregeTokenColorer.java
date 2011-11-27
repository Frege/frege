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
	protected final TextAttribute docuAttribute, identifierAttribute,
			keywordAttribute, numberAttribute;

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
				display.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.ITALIC);
		identifierAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		keywordAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
		numberAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);
		//      commentAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.ITALIC);
		//      stringAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD);
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		final TToken token = (TToken) o;
		final int tid = TToken.tokid(token).j;
		System.out.println("coloring " + IShow_Token.show(token));
		
		if (tid >= TTokenID.PACKAGE.j && tid <= TTokenID.INFIXR.j) 	return keywordAttribute;
		if (tid == TTokenID.CONID.j)								return docuAttribute; 
		if (tid == TTokenID.LEXERROR.j)
			return null;

		
		return super.getColoring(controller, token);
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}
}
