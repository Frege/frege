package frege.imp.parser;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import lpg.runtime.ILexStream;
import lpg.runtime.IPrsStream;
import lpg.runtime.IToken;
import lpg.runtime.LexStream;
import lpg.runtime.LpgLexStream;
import lpg.runtime.Monitor;
import lpg.runtime.PrsStream;


import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.ILexer;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IParser;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.MessageHandlerAdapter;
import org.eclipse.imp.parser.ParseControllerBase;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;

import frege.FregePlugin;

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

	public class ASTNodeToken extends ASTNode implements IASTNodeToken {
		public ASTNodeToken(IToken token) {
			super(token);
		}
	}

	public interface Visitor {
		boolean preVisit(ASTNode element);

		void postVisit(ASTNode element);

		boolean visit(ASTNodeToken n);

		void endVisit(ASTNodeToken n);
	}

	public static abstract class AbstractVisitor implements Visitor {
		public abstract void unimplementedVisitor(String s);

		public boolean preVisit(ASTNode element) {
			return true;
		}

		public void postVisit(ASTNode element) {
		}

		public boolean visit(ASTNodeToken n) {
			unimplementedVisitor("visit(ASTNodeToken)");
			return true;
		}

		public void endVisit(ASTNodeToken n) {
			unimplementedVisitor("endVisit(ASTNodeToken)");
		}
	}

	public interface IASTNodeToken {
		public IToken getLeftIToken();

		public IToken getRightIToken();

		void accept(Visitor v);
	}

	public class ASTNode implements IASTNodeToken {
		public ASTNode(IToken token) {
		}

		public ASTNode(IToken leftIToken, IToken rightIToken) {
		}

		public IToken getLeftIToken() {
			return null;
		}

		public IToken getRightIToken() {
			return null;
		}

		public void accept(Visitor v) {
		}
	}

	public class FregeLexer /* extends LpgLexStream  implements ILexer */ {
		public FregeLexer(String filename) throws java.io.IOException {
		}

		public LexStream getLexStream() {
			return null;
		}

		public void lexer(IProgressMonitor monitor, String what) {
		};

		public int[] getKeywordKinds() {
			return null;
		}

		public int getKind(int i) {
			return 0;
		}

		public String[] orderedExportedSymbols() {
			return null;
		}
	}

	public class FregeParser /* extends PrsStream */ implements IParser {
		public int numTokenKinds() {
			return 0;
		}

		public FregeParser(LexStream lexStream) {
		}

		public PrsStream getIPrsStream() {
			return (PrsStream) null;
		}

		public Object parser(IProgressMonitor monitor, int error_repair_count) {
			return null;
		}

		public int getEOFTokenKind() {
			return 0;
		}

		public void setMessageHandler(IMessageHandler errMsg) {
		}

		public void resolve(ASTNode root) {
		}
		
		/**
	     * @return array of keywords in the order in which they are mapped to integers.
	     */
	    public String[] orderedTerminalSymbols() {
	    	return frege.rt.Box.<String[]>box(frege.compiler.Scanner.keywordsByID._e()).j;
	    }

		@Override
		public void reset(ILexStream lexStream) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object parser(Monitor monitor, int error_repair_count) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private FregeParser parser;
	private FregeLexer lexer;
	private ASTNode currentAst;

	private char keywords[][];
	private boolean isKeyword[];

	/**
	 * @param filePath		Project-relative path of file
	 * @param project		Project that contains the file
	 * @param handler		A message handler to receive error messages (or any others)
	 * 						from the parser
	 */
	public void initialize(IPath filePath, ISourceProject project,
			IMessageHandler handler) {
		super.initialize(filePath, project, handler);
		IPath fullFilePath = project.getRawProject().getLocation()
				.append(filePath);
		createLexerAndParser(fullFilePath);

		parser.setMessageHandler(handler);
	}

	public IParser getParser() {
		return parser;
	}

	/* public ILexer getLexer() {
		return lexer;
	}*/

	public ISourcePositionLocator getNodeLocator() {
		return new FregeSourcePositionLocator(); // FregeASTNodeLocator();
	}

	public ILanguageSyntaxProperties getSyntaxProperties() {
		return null;
	}

	private void createLexerAndParser(IPath filePath) {
		try {
			lexer = new FregeLexer(filePath.toOSString());
			parser = new FregeParser(lexer.getLexStream());
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	/**
	 * setFilePath() should be called before calling this method.
	 */
	public Object parse(String contents, boolean scanOnly,
			IProgressMonitor monitor) {
		// PMMonitor my_monitor = new PMMonitor(monitor);
		char[] contentsArray = contents.toCharArray();

		// lexer.initialize(contentsArray, fFilePath.toPortableString());
		// parser.getParseStream().resetTokenStream();

		lexer.lexer(monitor, contents); // Lex the stream to produce the token stream
		if (monitor.isCanceled())
			return currentAst; // TODO currentAst might (probably will) be inconsistent wrt the lex stream now

		currentAst = (ASTNode) parser.parser(monitor, 0);
		parser.resolve(currentAst);

		// cacheKeywordsOnce();

		return currentAst;
	}

	@Override
	public Object parse(String input, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator getTokenIterator(IRegion region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISourcePositionLocator getSourcePositionLocator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
