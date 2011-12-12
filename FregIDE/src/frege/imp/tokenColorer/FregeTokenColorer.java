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
			normalAttribute, impAttribute,
			docuAttribute, conidAttribute, identAttribute,
			commentAttribute, specialAttribute, opAttribute,
			keywordAttribute, literalAttribute, errorAttribute;

	//  protected final TextAttribute commentAttribute, stringAttribute;

	public FregeTokenColorer() {
		super();
		
		Display display = Display.getDefault();
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		Color docuColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_DOCUCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_YELLOW).getRGB())); 
		Color commColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_COMMCOLOR),
						display.getSystemColor(SWT.COLOR_DARK_YELLOW).getRGB())); 
		Color conidColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_CONIDCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_RED).getRGB()));
		Color varidColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_VARIDCOLOR), 
						display.getSystemColor(SWT.COLOR_BLACK).getRGB()));
		Color impColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_GLOBALCOLOR), 
						display.getSystemColor(SWT.COLOR_BLUE).getRGB()));
		Color keywdColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_KEYWORDCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_MAGENTA).getRGB()));
		Color litColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_LITERALCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_CYAN).getRGB()));
		Color opColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_OPCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_GREEN).getRGB()));
		Color errColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_ERRORCOLOR), 
						display.getSystemColor(SWT.COLOR_RED).getRGB()));
		Color spcColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_SPECIALCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_MAGENTA).getRGB()));
		docuAttribute    = new TextAttribute(docuColor, null, SWT.ITALIC);
		commentAttribute = new TextAttribute(commColor, null, SWT.NORMAL);
		conidAttribute   = new TextAttribute(conidColor, null, SWT.NORMAL);
		normalAttribute  = new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		identAttribute   = new TextAttribute(varidColor, null, SWT.NORMAL);
		impAttribute     = new TextAttribute(impColor, null, SWT.NORMAL);
		keywordAttribute = new TextAttribute(keywdColor, null, SWT.BOLD);
		literalAttribute = new TextAttribute(litColor, null, SWT.NORMAL);
		opAttribute      = new TextAttribute(opColor, null, SWT.NORMAL);
		errorAttribute   = new TextAttribute(errColor, null, SWT.NORMAL);
		specialAttribute = new TextAttribute(spcColor, null, SWT.BOLD);
		
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
		if (tid == TTokenID.VARID.j || tid == TTokenID.QVARID.j)	return identAttribute;
		if (tid >= TTokenID.INTCONST.j && tid <= TTokenID.REGEXP.j) return literalAttribute;
		if (tid == TTokenID.LEXERROR.j) 							return errorAttribute;

		if (tid >= TTokenID.DCOLON.j && tid <= TTokenID.EARROW.j)	return specialAttribute;
		if (tid >= TTokenID.LOP0.j && tid <= TTokenID.SOMEOP.j) 	return opAttribute;
		if (tid == TTokenID.CHAR.j && TToken.value(token).length() > 0) 
			switch (TToken.value(token).charAt(0)) {
				case '_': return  specialAttribute;
				case '=': return  specialAttribute;
				case '|': return  specialAttribute;
				case '\\': return specialAttribute;
				case ';': return normalAttribute;
				case '(': return normalAttribute;
				case ')': return normalAttribute;
				case '[': return normalAttribute;
				case ']': return normalAttribute;
				case '{': return normalAttribute;
				case '}': return normalAttribute;
				case ',': return normalAttribute;
				case '.': return normalAttribute;
				case '!': return opAttribute;
				case '?': return opAttribute;
				case '-': return opAttribute;
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
