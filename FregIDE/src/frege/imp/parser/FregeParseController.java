package frege.imp.parser;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

// import lpg.runtime.ILexStream;
// import lpg.runtime.IPrsStream;
// import lpg.runtime.Monitor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNatureDescriptor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import org.eclipse.imp.builder.MarkerCreatorWithBatching;
import org.eclipse.imp.builder.ProblemLimit.LimitExceededException;
import org.eclipse.imp.model.IPathEntry;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IParser;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.ParseControllerBase;
import org.eclipse.imp.parser.SimpleAnnotationTypeInfo;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;
import org.osgi.service.prefs.BackingStoreException;

import frege.FregePlugin;
import frege.rt.Lambda;
import frege.rt.Box;
import frege.rt.FV;
import frege.rt.Lazy;
import frege.prelude.Base.TTuple2;
import frege.prelude.Base.TList;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TMessage;
import frege.compiler.Data.TOptions;
import frege.compiler.Data.TPosition;
import frege.compiler.Data.TSeverity;
import frege.compiler.Data.TStIO;
import frege.compiler.Data.TSubSt;
import frege.compiler.Data.TToken;
import frege.compiler.Data.TTokenID;
import frege.compiler.Data;

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

//	private IParser parser = new IParser() {
//		    /**
//		     * Run the parser to create a model.
//		     * @param monitor stop scanning/parsing when monitor.isCanceled() is true.
//		     * @return
//		     */
//		    public Object parser(Monitor monitor, int error_repair_count) { return null; }
//
//		    public IPrsStream getIPrsStream() { return null; }
//
//		    /**
//		     * @return array of keywords in the order in which they are mapped to integers.
//		     */
//		    public String[] orderedTerminalSymbols() { return Box.<String[]>box(frege.compiler.Scanner.keywordsByID._e()).j; }
//
//		    /**
//		     * @return array of keywords in the order in which they are mapped to integers.
//		     */
//		    public int numTokenKinds() { return TTokenID.LEXERROR.j; }
//
//		    /**
//		     * @return the token kind for the EOF token
//		     */
//		    public int getEOFTokenKind() { return TTokenID.LEXERROR.j; }
//
//		    public void reset(ILexStream lexStream) {}
//		
//	};
	private TGlobal global;
	private final ISourcePositionLocator   fSourcePositionLocator   
					= new FregeSourcePositionLocator();
    private final SimpleAnnotationTypeInfo fSimpleAnnotationTypeInfo
    				= new SimpleAnnotationTypeInfo();
	private IMessageHandler msgHandler = null;
	/**
	 * tell if we have errors
	 */
	public static int errors(TGlobal global) { return global == null ? 1 : TGlobal.errors(global); }
	
	/**
	 * run a {@link frege.compiler.data.TStIO} action
	 */
	public static TGlobal runStG(Lazy<FV> action, TGlobal g) {
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

		global =  (TGlobal) 
				frege.prelude.Base.TST.performUnsafe(
						(Lambda) frege.compiler.Main.standardOptions._e())._e();
		createLexerAndParser(fullFilePath, project);

		msgHandler = handler;
	}

	public IParser getParser() {
		new Exception("getParser: called").printStackTrace(System.out);
		return null; // parser;
	}

	
	public ISourcePositionLocator getNodeLocator() {
		return new FregeSourcePositionLocator(); // FregeASTNodeLocator();
	}
	

	@SuppressWarnings("unchecked")
	private void createLexerAndParser(IPath filePath, ISourceProject project) {
		System.err.println("createLexerAndParser: " + filePath.toPortableString());
		System.err.println("classpath: " + System.getProperty("java.class.path"));
		global = TGlobal.upd$options(global, TOptions.upd$source(
				TGlobal.options(global), 
				filePath.toPortableString()));

		String fp = ".";   
		String bp = "."; 
		String sp = ".";
		
		if (project != null) {
			IProject rp = project.getRawProject();
			// System.out.println("The raw project has type: " + jp.getClass());
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath wroot = workspace.getRoot().getLocation();
			// IProjectNatureDescriptor[] nds = workspace.getNatureDescriptors();
			boolean isJava = false;
			
			try {
					isJava = rp.hasNature("org.eclipse.jdt.core.javanature");
					
			} catch (CoreException e) {
					// e.printStackTrace();
					// System.out.println("The " + nd.getNatureId() + " is not supported, or so it seems.");
			}
			System.err.println("Our project "
					+ (isJava ? " has the " : " does not have ")
					+ " java nature.");
			if (isJava) {
				IJavaProject jp = JavaCore.create(rp);
				try {
					bp = wroot.append(jp.getOutputLocation()).toPortableString();
					IClasspathEntry[] cpes = jp.getResolvedClasspath(true);
					fp = "";
					sp = "";
					for (IClasspathEntry cpe: cpes) {
						if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
							if (sp.length() > 0) fp += System.getProperty("path.separator");
							sp += cpe.getPath().toPortableString();
						}
						else {
							if (fp.length() > 0) fp += System.getProperty("path.separator");
							fp += cpe.getPath().toPortableString();
						}
					}
				} catch (JavaModelException e) {
				} catch (NullPointerException np) {
				}
			}
		}
		if (fp == "") fp=".";
		if (sp == "") sp=".";
		
				
		System.err.println("FregePath: " + fp);
		global = TGlobal.upd$options(global, TOptions.upd$path(
				TGlobal.options(global),
				frege.prelude.Base.TRegex.splitted(
						((frege.rt.Box<Pattern>)frege.compiler.Utilities.pathRE._e()).j, 
						fp)));
		System.err.println("SourcePath: " + sp);
		global = TGlobal.upd$options(global, TOptions.upd$sourcePath(
				TGlobal.options(global),
				frege.prelude.Base.TRegex.splitted(
						((frege.rt.Box<Pattern>)frege.compiler.Utilities.pathRE._e()).j, 
						sp)));
		System.err.println("Destination: " + bp);
		global = TGlobal.upd$options(global, TOptions.upd$dir(
				TGlobal.options(global), 
				bp));
		global = runStG(frege.compiler.Main.newLoader, global);
	}

	/**
	 * The msgHandler must be in place
	 */
	public TGlobal parse(String contents, boolean scanOnly,
			IProgressMonitor monitor) {
		
		msgHandler.clearMessages();
		
		final IProgressMonitor myMonitor = monitor;
		
		Lambda cancel = new frege.rt.Lam1() {
			public Lazy<FV> eval(Lazy<FV> realworld) {
				return (myMonitor.isCanceled()) ? Box.Bool.t : Box.Bool.f;	
			}
		};
		
		global = TGlobal.upd$sub(global,  TSubSt.upd$cancelled(
				TGlobal.sub(global), 
				cancel));
		
		@SuppressWarnings("unchecked")
		Lazy<FV> actions[] = new Lazy[] {
				frege.compiler.Main.lexPassIDE(contents),
				frege.compiler.Main.parsePass,
				frege.compiler.Fixdefs.pass,
				frege.compiler.Import.pass,
				frege.compiler.Classes.passI(Box.Bool.t),
				frege.compiler.Enter.pass,
				frege.compiler.TAlias.pass,
				frege.compiler.Enter.pass2,
				frege.compiler.Enter.pass3,
				frege.compiler.Transdef.pass,
				frege.compiler.Classes.passC,
				frege.compiler.Classes.passI(Box.Bool.f),
				frege.compiler.Transform.pass7,
				frege.compiler.Typecheck.pass,
		};
		String names[] = new String[] {
			"lexical analysis",
			"syntax analysis",
			"collecting definitions",
			"symbol table initialization and import",
			"verify imported instances",
			"enter definitions",
			"check type aliases",
			"make field definitions",
			"enter (derived) instances",
			"translate names in exprs and types",
			"verify class definitions",
			"verify own instances",
			"simplify lets",
			"type check",
		};
		
		monitor.beginTask(this.getClass().getName() + " parsing", actions.length);
		
		long t0 = System.nanoTime();
		
		for (int i = 0; i < actions.length; i++) {
			long t1 = System.nanoTime();
			TGlobal g = runStG(actions[i], global);
			long te = System.nanoTime();
			System.err.println(names[i] + " took " 
				+ (te-t1)/1000000 + "ms, cumulative "
				+ (te-t0)/1000000 + "ms");
			if (errors(g) > 0) return g;
			global = g;
			if (monitor.isCanceled()) {
				System.err.println("cancelled in " + names[i]);
				return global;
			}
			monitor.worked(1);
		}
		
		return global;
	}

	@Override
	public Object getCurrentAst() {
		return global;
	}
	
	@Override
	public Object parse(String input, IProgressMonitor monitor) {
		MarkerCreatorWithBatching mcwb = msgHandler instanceof MarkerCreatorWithBatching ?
				(MarkerCreatorWithBatching) msgHandler : null;
		// when we build, we'll get a MarkerCreatorWithBatching
		// Hence, if we do not have one, we just scan&parse, otherwise we do a full compile
		TGlobal g = parse(input, mcwb == null, monitor);
		TList msgs = TSubSt.messages(TGlobal.sub(g));
		 
		while (true) {
			TList.DCons node = msgs._Cons();
			if (node == null) break;
			msgs = (TList) node.mem2._e();
			TMessage msg = (TMessage) node.mem1._e();
			if (mcwb != null) {
				// do also warnings and hints
				int sev = IMarker.SEVERITY_ERROR;
				if (TMessage.level(msg).j == TSeverity.HINT.j) sev = IMarker.SEVERITY_INFO;
				else if (TMessage.level(msg).j == TSeverity.WARNING.j)
					sev = IMarker.SEVERITY_WARNING;
				try {
					mcwb.addMarker(sev, TMessage.text(msg), 
							TToken.line( TPosition.first(TMessage.pos(msg)) ), 
							TPosition.start(TMessage.pos(msg)), 
							TPosition.end(TMessage.pos(msg))-1);
				} catch (LimitExceededException e) {
					break;
				}
				continue;
			}
			// normal message handling
			if (TMessage.level(msg).j != TSeverity.ERROR.j) continue;
			msgHandler.handleSimpleMessage(TMessage.text(msg), 
					TPosition.start(TMessage.pos(msg)), 
					TPosition.end(TMessage.pos(msg))-1, 
					0, 0, 0, 0);
		}
		monitor.done();
		return g;
	}
	
	static class TokensIterator implements Iterator<TToken> {
		/** current list node */
		private TList list;
		private IRegion region;
		/** check if token is within region */
		public static boolean within(TToken tok, IRegion region) {
			return (TToken.offset(tok) + TToken.value(tok).length() >= region.getOffset()
					&& TToken.offset(tok) <= region.getOffset() + region.getLength());
		}
		/** construct an Iterator */
		public TokensIterator(TList it, IRegion reg) { 
			list = it;
			region = reg;
			while (true) {
				TList.DCons cons = list._Cons();
				if (cons == null) break;
				TToken t = (TToken) cons.mem1._e();
				if (within(t, reg)) break;
				list = (TList) cons.mem2._e();
			}
		}
		
		@Override
		public boolean hasNext() {
			// we have a next if we are not the empty list and the token is in the region
			return list._Cons() != null
					&& within((TToken)list._Cons().mem1._e(), region);
		}
		@Override
		public TToken next() {
			// give back next token
			TList.DCons cons = list._Cons();
			if (cons != null) {
				list = (TList)  cons.mem2._e();
				return (TToken) cons.mem1._e();
			}
			return null;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException("TokensIterator");
		}
		
		
	}
	@Override
	public Iterator<Data.TToken> getTokenIterator(IRegion region) {
		System.out.println("getTokenIterator(): ");
		return new TokensIterator(TSubSt.toks(TGlobal.sub(global)), region);
		
		/*
		List<TToken> ts = new java.util.LinkedList<TToken>();
		 
		TList fts = TSubSt.toks(TGlobal.sub(global));
		while (true) {
			TList.DCons cons = fts._Cons();
			if (cons == null) break;
			TToken tok = (TToken) cons.mem1._e();
			if (TToken.offset(tok) + TToken.value(tok).length() >= region.getOffset()
					&& TToken.offset(tok) <= region.getOffset() + region.getLength()) ts.add(tok); 
			if (TToken.offset(tok) > region.getOffset() + region.getLength()) break;
			fts = (TList) cons.mem2._e();		
		}
		System.out.println(ts.size());
		return ts.iterator();
		*/
	}

	@Override
	public ISourcePositionLocator getSourcePositionLocator() {
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
					return "--";
				}
				
				@Override
				public String getIdentifierConstituentChars() {
					return null;
				}
				
				@Override
				public int[] getIdentifierComponents(String ident) {
					return null;
				}
				
				@Override
				public String[][] getFences() {
					return new String[][] { {"(", ")"}, {"{", "}"}, {"[", "]"}};
				}
				
				@Override
				public String getBlockCommentStart() {
					return "{-";
				}
				
				@Override
				public String getBlockCommentEnd() {
					return "-}";
				}
				
				@Override
				public String getBlockCommentContinuation() {
					return null;
				}
			};
		}
		return lsp;
	}
}
