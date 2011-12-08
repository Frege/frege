package frege.imp.tokenColorer;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import frege.FregePlugin;
import frege.compiler.Data.TToken;
import frege.compiler.Data.TTokenID;
import frege.compiler.Data.IShow_Token;
import frege.imp.preferences.FregePreferencesConstants;



public class FregeTokenColorer extends TokenColorerBase implements ITokenColorer {
	protected final TextAttribute
			normalAttribute,
			docuAttribute, conidAttribute, identifierAttribute,
			commentAttribute, specialAttribute, opAttribute,
			keywordAttribute, literalAttribute, errorAttribute;

	//  protected final TextAttribute commentAttribute, stringAttribute;

	public FregeTokenColorer() {
		super();
		
		int i = SWT.COLOR_DARK_CYAN;

		Display display = Display.getDefault();
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		Color docuColor = new Color (display, StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_DOCUCOLOR), 
											                        display.getSystemColor(SWT.COLOR_DARK_YELLOW).getRGB())); 
		Color commColor = new Color (display, StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_COMMCOLOR), 
                													display.getSystemColor(SWT.COLOR_DARK_YELLOW).getRGB())); 
		docuAttribute    = new TextAttribute(docuColor, null, SWT.ITALIC);
		commentAttribute = new TextAttribute(commColor, null, SWT.NORMAL);
		conidAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.NORMAL);
		normalAttribute = new TextAttribute(
				display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
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
		if (tid == TTokenID.CONID.j 
				|| tid == TTokenID.QUALIFIER.j
				|| tid == TTokenID.QCONID.j)	return conidAttribute;
		if (tid == TTokenID.VARID.j || tid == TTokenID.QVARID.j)	return identifierAttribute;
		if (tid >= TTokenID.INTCONST.j && tid <= TTokenID.REGEXP.j) return literalAttribute;
		if (tid == TTokenID.LEXERROR.j) 							return errorAttribute;

		if (tid >= TTokenID.DCOLON.j && tid <= TTokenID.EARROW.j)	return specialAttribute;
		if (tid >= TTokenID.LOP0.j && tid <= TTokenID.SOMEOP.j) 	return opAttribute;
		if (tid == TTokenID.CHAR.j) switch (TToken.value(token)) {
		case "_": return  specialAttribute;
		case "=": return  specialAttribute;
		case "|": return  specialAttribute;
		case "\\": return specialAttribute;
		case ";": return normalAttribute;
		case "(": return normalAttribute;
		case ")": return normalAttribute;
		case "[": return normalAttribute;
		case "]": return normalAttribute;
		case "{": return normalAttribute;
		case "}": return normalAttribute;
		case ",": return normalAttribute;
		case ".": return normalAttribute;
		case "!": return opAttribute;
		case "?": return opAttribute;
		case "-": return opAttribute;
		default: break;
		}
		System.err.println("Don't know how to colour " + IShow_Token.show(token) + " ?");
		return super.getColoring(controller, token);
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		System.err.println("calculateDamagExtent: " + seed);
		return seed;
	}
}
