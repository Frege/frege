package frege.imp.parser;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lpg.runtime.ILexStream;
import lpg.runtime.IPrsStream;
import lpg.runtime.Monitor;

/*
import lpg.runtime.ILexStream;
import lpg.runtime.IPrsStream;
import lpg.runtime.IToken;
import lpg.runtime.LexStream;
import lpg.runtime.LpgLexStream;
import lpg.runtime.Monitor;
import lpg.runtime.PrsStream;
*/

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.imp.model.IPathEntry;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.ILexer;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IParser;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.MessageHandlerAdapter;
import org.eclipse.imp.parser.ParseControllerBase;
import org.eclipse.imp.parser.SimpleAnnotationTypeInfo;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;

import frege.FregePlugin;
import frege.compiler.Data;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TTokenID;
import frege.compiler.Data.TToken;
import frege.compiler.Data.TSubSt;
import frege.compiler.Data.TOptions;
import frege.compiler.Data.TStIO;
import frege.imp.preferences.FregeConstants;
import frege.rt.Lambda;
import frege.rt.Box;
import frege.rt.FV;
import frege.rt.Lazy;
import frege.prelude.Base.TTuple2;
import frege.prelude.Base.TList;

/**
 * NOTE:  This version of the Parse Controller is for use when the Parse
 * Controller and corresponding Node Locator are generated separately from
 * a corresponding set of LPG grammar templates and possibly in the absence
 * of the lexer, parser, and AST-related types that would be generated from
 * those templates.  It is assumed that either a) the Controller will be
 * used with a suitable set of lexer, parser, and AST-related types
 * that are provided by some means other than LPG, or b) the Controller will
 * be used with a set of lexer, parser, and AST types that have been, or will
 * be, separately generated based on LPG.  In order to enable this version of
 * the Parse Controller to compile, dummy lexer, parser, and AST-related types
 * have been included as member types in the Controller.  These types are not
 * operational and are merely placeholders for types that would support a
 * functioning implementation.  Apart from the inclusion of these dummy types,
 * this representation of the Parse Controller is the same as that used
 * with LPG.
 * 	
 * @author Stan Sutton (suttons@us.ibm.com)
 * @since May 1,  2007	Addition of marker types
 * @since May 10, 2007	Conversion IProject -> ISourceProject
 * @since May 15, 2007	Addition of dummy types
 */
public class FregeParseController extends ParseControllerBase implements
		IParseController {

	public FregeParseController() {
		super(FregePlugin.getInstance().getLanguageID());
	}

	private IParser parser = new IParser() {
		    /**
		     * Run the parser to create a model.
		     * @param monitor stop scanning/parsing when monitor.isCanceled() is true.
		     * @return
		     */
		    public Object parser(Monitor monitor, int error_repair_count) { return null; }

		    public IPrsStream getIPrsStream() { return null; }

		    /**
		     * @return array of keywords in the order in which they are mapped to integers.
		     */
		    public String[] orderedTerminalSymbols() { return Box.<String[]>box(frege.compiler.Scanner.keywordsByID._e()).j; }

		    /**
		     * @return array of keywords in the order in which they are mapped to integers.
		     */
		    public int numTokenKinds() { return TTokenID.LEXERROR.j; }

		    /**
		     * @return the token kind for the EOF token
		     */
		    public int getEOFTokenKind() { return TTokenID.LEXERROR.j; }

		    public void reset(ILexStream lexStream) {}
		
	};
	private TGlobal global = (TGlobal) frege.prelude.Base.TST.performUnsafe((Lambda) frege.compiler.Main.standardOptions._e())._e();
	private ISourcePositionLocator fSourcePositionLocator = new FregeSourcePositionLocator();
    private final SimpleAnnotationTypeInfo fSimpleAnnotationTypeInfo= new SimpleAnnotationTypeInfo();
	
	/**
	 * tell if we have errors
	 */
	public int errors() { return global == null ? 1 : TGlobal.errors(global); }
	
	/**
	 * run a {@link frege.compiler.data.TStIO} action
	 */
	public TGlobal runStG(Lazy<FV> action, TGlobal g) {
		Lambda stg = (Lambda) action._e();				// StIO (g -> IO (a, g) 
		TTuple2 r = (TTuple2)TStIO.performUnsafe(stg, g)._e();
		return (TGlobal) r.mem2._e();
	}

	/**
	 * @param filePath		Project-relative path of file
	 * @param project		Project that contains the file
	 * @param handler		A message handler to receive error messages (or any others)
	 * 						from the parser
	 */
	public void initialize(IPath filePath, ISourceProject project,
			IMessageHandler handler) {
		super.initialize(filePath, project, handler);
		IPath fullFilePath = project != null ?
				project.getRawProject().getLocation().append(filePath)
				: filePath;
		System.out.print("BuildPath: ");
		if (project != null)
			for (IPathEntry ip: project.getBuildPath()) 
				System.out.print(ip.getPath().toPortableString() + ", ");
		System.out.println();
		createLexerAndParser(fullFilePath, project);

		// parser.setMessageHandler(handler);
	}

	public IParser getParser() {
		new Exception("getParser: called").printStackTrace(System.out);
		return parser;
	}

	/*
	public ISourcePositionLocator getNodeLocator() {
		return new FregeSourcePositionLocator(); // FregeASTNodeLocator();
	}
	*/

	private void createLexerAndParser(IPath filePath, ISourceProject project) {
		System.out.println("createLexerAndParser: " + filePath.toPortableString());
		System.out.println("classpath: " + System.getProperty("java.class.path"));
		global = TGlobal.upd$options(global, TOptions.upd$source(
				TGlobal.options(global), 
				filePath.toPortableString()));
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		service.setLanguageName("frege");
		if (project != null) service.setProject(project.getRawProject());
		String fp = service.getStringPreference(FregeConstants.P_FREGEPATH);
		String bp = service.getStringPreference(FregeConstants.P_DESTINATION);
		System.out.println("FregePath: " + fp);
		global = TGlobal.upd$options(global, TOptions.upd$path(
				TGlobal.options(global), 
				TList.DCons.mk(Box.mk(fp), TList.DList.mk())));
		System.out.println("Destination: " + bp);
		global = TGlobal.upd$options(global, TOptions.upd$dir(
				TGlobal.options(global), 
				bp));
	}

	/**
	 * setFilePath() should be called before calling this method.
	 */
	public Object parse(String contents, boolean scanOnly,
			IProgressMonitor monitor) {
		// PMMonitor my_monitor = new PMMonitor(monitor);
		// char[] contentsArray = contents.toCharArray();
		int i = contents.indexOf('\n');
		System.out.println("Frege parse(´" 
				+ (i < 0 ? contents : contents.substring(0,  i)) 
				+ "´, " 
				+ scanOnly  + ")");
		
		Lambda lexPass = frege.compiler.Main.lexPassIDE(contents);
		global = runStG(lexPass, global);
		if (errors() > 0) return null;
		else if (monitor.isCanceled()) {
			System.out.println("after lex ... cancelled");
			return global;
		}
		
		
		if (errors() > 0) return null;
		else {
			global = runStG(frege.compiler.Main.parsePass, global);
			if (monitor.isCanceled()) {
				System.out.println("after parse ... cancelled");
				return global;
			}
		}
		
		return global;
	}

	@Override
	public Object getCurrentAst() {
		return global;
	}
	
	@Override
	public Object parse(String input, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return parse(input, false, monitor);
	}

	@Override
	public Iterator<Data.TToken> getTokenIterator(IRegion region) {
		System.out.println("getTokenIterator()");
		if (errors() > 0) return null;
		List<TToken> ts = new java.util.LinkedList<TToken>();
		TList fts = TSubSt.toks( TGlobal.sub(global) );
		while (true) {
			TList.DCons cons = fts._Cons();
			if (cons == null) break;
			ts.add((TToken) cons.mem1._e());
		}
		return ts.iterator();
	}

	@Override
	public ISourcePositionLocator getSourcePositionLocator() {
		// TODO Auto-generated method stub
		return fSourcePositionLocator;
	}

	@Override
	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		return fSimpleAnnotationTypeInfo;
	}
	
	private ILanguageSyntaxProperties lsp;
	/**
     * @return an implementation of {@link ILanguageSyntaxProperties} that
     * describes certain syntactic features of this language
     */
	@Override 
	public ILanguageSyntaxProperties getSyntaxProperties() {
		if (lsp == null) {
			lsp = new ILanguageSyntaxProperties() {
				
				@Override
				public String getSingleLineCommentPrefix() {
					// TODO Auto-generated method stub
					return "--";
				}
				
				@Override
				public String getIdentifierConstituentChars() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int[] getIdentifierComponents(String ident) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String[][] getFences() {
					// TODO Auto-generated method stub
					return new String[][] { {"(", ")"}, {"{", "}"}, {"[", "]"}};
				}
				
				@Override
				public String getBlockCommentStart() {
					// TODO Auto-generated method stub
					return "{-";
				}
				
				@Override
				public String getBlockCommentEnd() {
					// TODO Auto-generated method stub
					return "-}";
				}
				
				@Override
				public String getBlockCommentContinuation() {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
		return lsp;
	}
}
