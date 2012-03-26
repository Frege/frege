package frege.imp.documentation;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.IDocumentationProvider;

import frege.rt.Array;
import frege.rt.Lambda;
import frege.rt.Box;
import frege.rt.FV;
import frege.rt.Lazy;
import frege.prelude.PreludeBase.TList.DCons;
import frege.prelude.PreludeBase.TTuple2;
import frege.prelude.PreludeBase.TList;
import frege.prelude.PreludeBase.TTuple3;
import frege.prelude.PreludeBase.TState;
import frege.prelude.PreludeList.IListLike__lbrack_rbrack;
import frege.compiler.BaseTypes.TFlag;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TMessage;
import frege.compiler.Data.TOptions;
import frege.compiler.BaseTypes.TPosition;
import frege.compiler.Data.TSeverity;
import frege.compiler.Data.TSubSt;
import frege.compiler.BaseTypes.TToken;
import frege.compiler.BaseTypes.TTokenID;
import frege.compiler.Data;
import frege.compiler.Utilities;
import frege.compiler.EclipseUtil;
import frege.compiler.Main;


import frege.imp.parser.FregeParseController;
import frege.imp.referenceResolvers.FregeReferenceResolver;

public class FregeDocumentationProvider implements IDocumentationProvider {
	public String getDocumentation(Object entity, IParseController ctlr) {
		if (entity == null)
			return null;

		if (entity instanceof FregeReferenceResolver.Symbol
				|| entity instanceof FregeReferenceResolver.Namespace) {
			final String s = entity.toString();
			// System.err.println("MARKUP: " + s);
			return s;
		}
		return null;
	}

}
