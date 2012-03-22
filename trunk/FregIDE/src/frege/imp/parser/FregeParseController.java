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
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNatureDescriptor;
import org.eclipse.core.resources.IResource;
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
import frege.compiler.EclipseUtil;
import frege.compiler.Main;
import frege.imp.builders.FregeBuilder;
import frege.imp.builders.FregeBuilderBase;
import frege.imp.preferences.FregePreferencesConstants;

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

	public static class TokensIterator implements Iterator<TToken> {
		/** current token array */
		final private Array<FV> toks;
		private IRegion region;
		private int  inx;
		
		/** check if token is within region */
		public static boolean within(TToken tok, IRegion region) {
			return (TToken.offset(tok) + TToken.value(tok).length() >= region.getOffset()
					&& TToken.offset(tok) <= region.getOffset() + region.getLength());
		}
		
		/** construct an Iterator */
		public TokensIterator(Array<FV> it, IRegion reg) { 
			toks = it;
			region = reg;
			inx = 0;
			while (inx < toks.length()) {
				TToken t = (TToken) toks.getAt(inx)._e(); 
				if (within(t, reg)) break;
				inx++;
			}
		}
		
		public static int skipBraces(final Array<FV> toks, int j) {
			while (j < toks.length()) {
				TToken tok = (TToken) toks.getAt(j)._e();
				if (tok.mem1 == TTokenID.CHAR.j
						&& (tok.mem2.charAt(0) == '{'
								|| tok.mem2.charAt(0) == '}'
								|| tok.mem2.charAt(0) == ';')) {
					j++;
				}
				else break;
			}
			return j;
		}
		
		@Override
		public boolean hasNext() {
			// skip { ; }
			inx = skipBraces(toks, inx);
			// we have a next if we are not the empty list and the token is in the region
			return inx < toks.length()
					&& within((TToken)toks.getAt(inx)._e(), region);
		}
		
		@Override
		public TToken next() {
			// give back next token
			if (inx < toks.length()) {
				return (TToken) toks.getAt(inx++)._e();
			}
			return null;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("TokensIterator");
		}		
	}
	
	public static class FregeData {
		private String sp = ".";
		private String fp = ".";
		private String bp = ".";
		private ISourceProject project = null;
		public FregeData(ISourceProject sourceProject) {
			project = sourceProject;
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

				if (isJava) {
					IJavaProject jp = JavaCore.create(rp);
					try {
						bp = wroot.append(jp.getOutputLocation()).toPortableString();
						IClasspathEntry[] cpes = jp.getResolvedClasspath(true);
						fp = "";
						sp = ".";
						for (IClasspathEntry cpe: cpes) {
							if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
								if (sp.length() > 0) sp += System.getProperty("path.separator");
								sp += cpe.getPath().makeRelativeTo(jp.getPath()).toString();
							}
							else {
								if (fp.length() > 0) fp += System.getProperty("path.separator");
								fp += cpe.getPath().toString();
							}
						}
					} catch (JavaModelException e) {
					} catch (NullPointerException np) {
					}
				}
			}
			if (fp.equals("")) fp=".";
			if (sp.equals("")) sp=".";
		}
		/**
		 * The source path always includes the project directory, as otherwise source resolution in
		 * linked directories will work only if one links below a source directory, which may not be
		 * possible always.
		 * @return the sp
		 */
		public String getSp() {
			return sp;
		}
		/**
		 * @return the fp
		 */
		public String getFp() {
			return fp;
		}
		/**
		 * @return the bp
		 */
		public String getBp() {
			return bp;
		}
		/**
		 * look for a path that contains the source code for pack
		 */
		public IPath getSource(final String pack) {
			final String fr = pack.replaceAll("\\.", "/") + ".fr";
			// final String[] srcs = getSp().split(System.getProperty("path.separator"));
			final FregeBuilder builder = new FregeBuilder();
			try {
				project.getRawProject().getWorkspace().getRoot().accept(builder.fResourceVisitor);
			} catch (CoreException e) {
				// problems getting the file names
				return null;
			}
			for (IFile file : builder.fChangedSources) {
				IPath it = file.getFullPath();
				if (it.toString().endsWith(fr)) return it;
			}
			/*
			for (String sf: srcs) {
				final IPath p = new Path(sf + "/" + fr);
				
				final IResource toRes = project.getRawProject().findMember(p);  
				
				if (toRes != null && toRes instanceof IFile) {
					final IFile to = (IFile) toRes;
					return to.getFullPath();
				}
			}
			*/
			return null;
		}
	}
	
	public FregeParseController() {
		super(FregePlugin.getInstance().getLanguageID());
	}

	private int timeout;
	private TGlobal global;
	private int  hash = 0;
	private int  leng = 0;
	private final ISourcePositionLocator   fSourcePositionLocator   
					= new FregeSourcePositionLocator(this);
    private final SimpleAnnotationTypeInfo fSimpleAnnotationTypeInfo
    				= new SimpleAnnotationTypeInfo();
	private IMessageHandler msgHandler = null;
	private FregeData fregeData = null;
	
	/**
	 * @author ingo
	 * @return the frege data structure
	 */
	public FregeData getFD() { return fregeData; }
	/**
	 * tell if we have errors
	 */
	public static int errors(TGlobal global) { return global == null ? 1 : TGlobal.errors(global); }
	
	/**
	 * tell how far we are advanced
	 */
	public static int achievement(TGlobal global) {
		if (global == null) return 0;
		final TSubSt sub = TGlobal.sub(global);
		return 2 * TSubSt.nextPass(sub) - (errors(global) > 0 ? 1 : 0);
	}
	
	/**
	 * run a {@link frege.prelude.PreludeBase.TState} action and return the new TGlobal state
	 * @return the new state
	 */
	public static TGlobal runStG(Lazy<FV> action, TGlobal g) {
		Lambda stg = (Lambda) action._e();				// State (g -> (a, g)) 
		TTuple2 r = (TTuple2)TState.run(stg, g)._e();
		return (TGlobal) r.mem2._e();
	}
	
	/**
	 * Run a {@link frege.prelude.PreludeBase.TState} action and return the result.
	 * The state must not be changed by the action. 
	 * @return the result
	 */
	public static FV funStG(Lazy<FV> action, TGlobal g) {
		Lambda stg = (Lambda) action._e();				// State (g -> (a, g)) 
		TTuple2 r = (TTuple2)TState.run(stg, g)._e();
		return r.mem1._e();
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
				frege.prelude.PreludeBase.TST.performUnsafe(
						(Lambda) frege.compiler.Main.standardOptions._e())._e();
		fregeData = new FregeData(project);
		createLexerAndParser(fullFilePath, project);

		msgHandler = handler;
	}

	public IParser getParser() {
		new Exception("getParser: called").printStackTrace(System.out);
		return null; // parser;
	}

	
	public ISourcePositionLocator getNodeLocator() {
		return fSourcePositionLocator;
	}
	

	
	private void createLexerAndParser(IPath filePath, ISourceProject project) {
		System.err.println("createLexerAndParser: " + filePath.toPortableString());
		System.err.println("classpath: " + System.getProperty("java.class.path"));
		global = TGlobal.upd$options(global, TOptions.upd$source(
				TGlobal.options(global), 
				filePath.toPortableString()));

		final FregeData data = fregeData;
		final String fp = data.getFp();   
		final String bp = data.getBp(); 
		final String sp = data.getSp();
		
				
		System.err.println("FregePath: " + fp);
		global = TGlobal.upd$options(global, TOptions.upd$path(
				TGlobal.options(global),
				frege.prelude.Arrays.IStringSplitter_Regex.splitted(
						((frege.rt.Box<Pattern>)frege.compiler.Utilities.pathRE._e()).j, 
						fp)));
		System.err.println("SourcePath: " + sp);
		global = TGlobal.upd$options(global, TOptions.upd$sourcePath(
				TGlobal.options(global),
				frege.prelude.Arrays.IStringSplitter_Regex.splitted(
						((frege.rt.Box<Pattern>)frege.compiler.Utilities.pathRE._e()).j, 
						sp)));
		System.err.println("Destination: " + bp);
		global = TGlobal.upd$options(global, TOptions.upd$dir(
				TGlobal.options(global), 
				bp));
		global = runStG(frege.compiler.Main.newLoader, global);
		
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		if (service != null) {
			timeout = service.getIntPreference(FregePreferencesConstants.P_PARSETIMEOUT);
		}
		else timeout = 250;
	}

	/**
	 * The msgHandler must be in place
	 */
	public TGlobal parse(String contents, boolean scanOnly,
			IProgressMonitor monitor) {
		
		if (scanOnly && timeout > 0)
			try { Thread.sleep(timeout); } catch (InterruptedException e) {}
		if (monitor.isCanceled()) return global;
		
		if (contents.length() == leng && contents.hashCode() == hash)
			return global;			// nothing really updated here
		
		
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
		
//		if (!scanOnly) {
//			System.err.println("parse for build");
//			global = runStG(frege.compiler.Main.withOption(TFlag.WITHCP), global);
//		}
		
		
		long t0 = System.nanoTime();
		TList passes = (TList) frege.compiler.Main.passes._e();
		monitor.beginTask(this.getClass().getName() + " parsing", 
				1 + IListLike__lbrack_rbrack.length(passes));
		
		int index = 0;
		while (!monitor.isCanceled()) {
			long t1 = System.nanoTime();
			index++;
			final DCons pass = passes._Cons();
			if (pass== null) break;   // done
			passes = (TList) pass.mem2._e();
			final TTuple3 adx = (TTuple3) pass.mem1._e();
			final Lazy<FV> action = index == 1 ? Main.lexPassIDE(contents) : adx.mem1;
			final String   desc   = Box.<String>box(adx.mem2._e()).j;
			final TGlobal g = runStG(action, global);
			long te = System.nanoTime();
			System.err.println(desc + " took " 
				+ (te-t1)/1000000 + "ms, cumulative "
				+ (te-t0)/1000000 + "ms");
			monitor.worked(1);
			global = runStG(EclipseUtil.passDone._e(), g);
			if (monitor.isCanceled()) {
				System.err.println("cancelled in " + desc);
				break;
			}
			
			if (errors(g) > 0) break;
			if (scanOnly && desc.startsWith("type check")) break;
		}
		
		leng = contents.length();
		hash = contents.hashCode();
		return global;
	}

	@Override
	public TGlobal getCurrentAst() {
		return global;
	}
	
	@Override
	public TGlobal parse(String input, IProgressMonitor monitor) {
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
					mcwb.addMarker(sev, 
							TMessage.text(msg)
								.replaceAll("\n", "   "), 
							TToken.line( TPosition.first(TMessage.pos(msg)) ), 
							TPosition.start(TMessage.pos(msg)), 
							TPosition.end(TMessage.pos(msg)));
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
		if (mcwb == null) monitor.done();
		return g;
	}
	
	@Override
	public Iterator<TToken> getTokenIterator(IRegion region) {
		System.err.println("getTokenIterator(): ");
		return new TokensIterator(TSubSt.toks(TGlobal.sub(global)), region);
		
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
