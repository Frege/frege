package frege.imp.tokenColorer;

import java.util.regex.Pattern;

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
import frege.compiler.Data.TQName.DLocal;
import frege.compiler.Data.TSubSt;
import frege.compiler.BaseTypes.TToken;
import frege.compiler.Data.TGlobal;
import frege.compiler.BaseTypes.TTokenID;
import frege.compiler.BaseTypes.IShow_Token;
import frege.compiler.Data.TQName;
import frege.imp.parser.FregeParseController;
import frege.imp.preferences.FregePreferencesConstants;
import frege.prelude.PreludeBase.TEither;
import frege.prelude.PreludeBase.TEither.DRight;
import frege.prelude.PreludeBase.TMaybe;



public class FregeTokenColorer extends TokenColorerBase implements ITokenColorer {
	protected final TextAttribute
			normalAttribute, impAttribute, iopAttribute,
			identAttribute, docuAttribute,
			commentAttribute, specialAttribute, 
			keywordAttribute, literalAttribute, errorAttribute,
			nsAttribute, typeAttribute, itypeAttribute, 
			conAttribute, iconAttribute;
	final Pattern pattern = Pattern.compile("^\\W+$");

	//  protected final TextAttribute commentAttribute, stringAttribute;

	public FregeTokenColorer() {
		super();
		
		Display display = Display.getDefault();
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		
		final boolean boldns = service.getBooleanPreference(FregePreferencesConstants.P_BOLDNS);
		final boolean italic = service.getBooleanPreference(FregePreferencesConstants.P_ITALICIMPORTS);
		
		Color docuColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_DOCUCOLOR),
						display.getSystemColor(SWT.COLOR_DARK_BLUE).getRGB())); 
		Color commColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_COMMCOLOR),
						display.getSystemColor(SWT.COLOR_DARK_CYAN).getRGB())); 
		Color tconColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_TCONCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_RED).getRGB()));
		Color dconColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_DCONCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_YELLOW).getRGB()));
		Color varidColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_VARIDCOLOR), 
						display.getSystemColor(SWT.COLOR_BLACK).getRGB()));
		Color importColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_IMPORTCOLOR), 
						display.getSystemColor(SWT.COLOR_BLACK).getRGB()));
		Color keywdColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_KEYWORDCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_MAGENTA).getRGB()));
		Color litColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_LITERALCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_CYAN).getRGB()));
		Color errColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_ERRORCOLOR), 
						display.getSystemColor(SWT.COLOR_RED).getRGB()));
		Color spcColor = new Color (display, 
				StringConverter.asRGB(service.getStringPreference(FregePreferencesConstants.P_SPECIALCOLOR), 
						display.getSystemColor(SWT.COLOR_DARK_MAGENTA).getRGB()));
		commentAttribute = new TextAttribute(commColor, null, SWT.NORMAL);
		docuAttribute    = new TextAttribute(docuColor, null, SWT.ITALIC);
		normalAttribute  = new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		keywordAttribute = new TextAttribute(keywdColor, null, SWT.BOLD);
		literalAttribute = new TextAttribute(litColor, null, SWT.NORMAL);
		errorAttribute   = new TextAttribute(errColor, null, SWT.NORMAL);
		specialAttribute = new TextAttribute(spcColor, null, SWT.BOLD);
		
		identAttribute   = new TextAttribute(varidColor, null, SWT.NORMAL);
		impAttribute     = new TextAttribute(importColor, null, italic ? SWT.ITALIC : SWT.NORMAL);
		iopAttribute     = new TextAttribute(importColor, null, SWT.NORMAL);
		nsAttribute      = new TextAttribute(tconColor,  null, boldns ? SWT.BOLD : SWT.NORMAL);
		typeAttribute	 = new TextAttribute(tconColor,  null, SWT.NORMAL);
		itypeAttribute   = new TextAttribute(tconColor,  null, italic ? SWT.ITALIC : SWT.NORMAL);
		conAttribute     = new TextAttribute(dconColor,  null, SWT.NORMAL);
		iconAttribute    = new TextAttribute(dconColor,  null, italic ? SWT.ITALIC : SWT.NORMAL);
	}
	
	public TextAttribute getKind(FregeParseController controller, TToken tok, TextAttribute normalAttribute) {
		TGlobal g = controller.getCurrentAst();
		final TMaybe mb = (TMaybe) TGlobal.resolved(g, tok)._e();
		final TMaybe.DJust just = mb._Just();
		if (just == null) return normalAttribute;
		final TEither et = (TEither) just.mem1._e();
		final DRight right = et._Right();
		if (right == null) return nsAttribute;			// since it is Left ()
		final TQName qname = (TQName) right.mem1._e();
		final DLocal local = qname._Local();
		if (local != null) return normalAttribute;		// local var
		final boolean our = TQName.M.our(qname, g);
		final TQName.DTName tname = qname._TName();
		if (tname != null) return our? typeAttribute : itypeAttribute;
		final TQName.DMName mname = qname._MName();
		if (mname != null && TToken.tokid(tok).j == TTokenID.CONID.j)
			return our ? conAttribute : iconAttribute;
		final String b = TQName.M.base(qname);
		final boolean op = pattern.matcher(b).find();
		return our ? identAttribute : (op ? iopAttribute : impAttribute);
	}

	@Override 
	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		return getColoring((FregeParseController) controller, (TToken) o);
	}
	
	public TextAttribute getColoring(final FregeParseController controller, final TToken token) {
		final int tid = TToken.tokid(token).j;
		
		if (tid >= TTokenID.PACKAGE.j && tid <= TTokenID.INFIXR.j) 	return keywordAttribute;
		if (tid == TTokenID.DOCUMENTATION.j)						return docuAttribute;
		if (tid == TTokenID.COMMENT.j)								return commentAttribute;
		if (tid == TTokenID.CONID.j 
				|| tid == TTokenID.QUALIFIER.j
				|| tid == TTokenID.VARID.j) {
			return  getKind(controller, token, normalAttribute);
		}
			
		// if (tid == TTokenID.VARID.j || tid == TTokenID.QVARID.j)	return identAttribute;
		if (tid >= TTokenID.INTCONST.j && tid <= TTokenID.REGEXP.j) return literalAttribute;
		if (tid == TTokenID.LEXERROR.j) 							return errorAttribute;

		if (tid >= TTokenID.DCOLON.j && tid <= TTokenID.EARROW.j)	return specialAttribute;
		if (tid >= TTokenID.LOP0.j && tid <= TTokenID.SOMEOP.j) 	
			return getKind(controller, token, normalAttribute);
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
				case '!': return identAttribute;
				case '?': return identAttribute;
				case '-': return identAttribute;
				default: break;
		}
		// System.err.println("Don't know how to colour " + IShow_Token.show(token) + " ?");
		return errorAttribute;
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		System.err.println("calculateDamagExtent: " + seed);
		return seed;
	}
}
