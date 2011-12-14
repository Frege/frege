package frege.imp.builders;

import java.io.PrintWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.imp.builder.BuilderUtils;
import org.eclipse.imp.builder.MarkerCreator;
import org.eclipse.imp.builder.MarkerCreatorWithBatching;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.builder.ProblemLimit.LimitExceededException;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.runtime.PluginBase;

import frege.FregePlugin;
import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TMessage;
import frege.compiler.Data.TOptions;
import frege.compiler.Data.TPosition;
import frege.compiler.Data.TToken;
import frege.compiler.Main;
import frege.imp.parser.FregeParseController;
import frege.prelude.Base;
import frege.prelude.Base.TList;
import frege.prelude.Text;
import frege.rt.Box;

/**
 * A builder may be activated on a file containing frege code every time it
 * has changed (when "Build automatically" is on), or when the programmer
 * chooses to "Build" a project.
 * 
 * TODO This default implementation was generated from a template, it needs to
 * be completed manually.
 */
public class FregeBuilder extends BuilderBase {
	
	public FregeBuilder() {
		super();
	}

	/**
	 * Extension ID of the Frege builder, which matches the ID in
	 * the corresponding extension definition in plugin.xml.
	 */
	public static final String BUILDER_ID = FregePlugin.kPluginID +
			".frege.builder";

	/**
	 * A marker ID that identifies problems detected by the builder
	 */
	public static final String PROBLEM_MARKER_ID = FregePlugin.kPluginID
			+ ".frege.problem";
	public static final String WARNING_MARKER_ID = PROBLEM_MARKER_ID;
	public static final String INFO_MARKER_ID = PROBLEM_MARKER_ID;

	public static final String LANGUAGE_NAME = FregePlugin.kLanguageID;

	public static final Language LANGUAGE = LanguageRegistry
			.findLanguage(LANGUAGE_NAME);

	@Override public PluginBase getPlugin() {
		return FregePlugin.getInstance();
	}

	@Override public String getBuilderID() {
		return BUILDER_ID;
	}

	@Override public String getErrorMarkerID() {
		return PROBLEM_MARKER_ID;
	}

	@Override public String getWarningMarkerID() {
		return WARNING_MARKER_ID;
	}

	@Override public String getInfoMarkerID() {
		return INFO_MARKER_ID;
	}

	/**
	 * Decide whether a file needs to be build using this builder. Note that
	 * <code>isNonRootSourceFile()</code> and <code>isSourceFile()</code>
	 * should never return true for the same file.
	 * 
	 * @return true iff an arbitrary file is a frege source file.
	 */
	@Override public boolean isSourceFile(IFile file) {
		IPath path = file.getRawLocation();
		if (path == null)
			return false;

		String pathString = path.toString();
		if (pathString.indexOf("/bin/") != -1)
			return false;

		return LANGUAGE.hasExtension(path.getFileExtension());
	}

	/**
	 * Decide whether or not to scan a file for dependencies. Note:
	 * <code>isNonRootSourceFile()</code> and <code>isSourceFile()</code>
	 * should never return true for the same file.
	 * 
	 * @return true iff the given file is a source file that this builder should
	 *         scan for dependencies, but not compile as a top-level compilation
	 *         unit.
	 * 
	 */
	@Override public boolean isNonRootSourceFile(IFile resource) {
		return false;
	}

	/**
	 * Collects compilation-unit dependencies for the given file, and records
	 * them via calls to <code>fDependency.addDependency()</code>.
	 */
	@Override public void collectDependencies(IFile file) {
		String fromPath = file.getFullPath().toString();

		getPlugin().writeInfoMsg(
				"Collecting dependencies from frege file: " + file.getName());

		// TODO: implement dependency collector
		// E.g. for each dependency:
		// fDependencyInfo.addDependency(fromPath, uponPath);
	}

	/**
	 * @return true iff this resource identifies the output folder
	 */
	@Override  public boolean isOutputFolder(IResource resource) {
		return resource.getFullPath().lastSegment().equals("bin");
	}

	/**
	 * Compile one frege file.
	 */
	@Override public void compile(final IFile file, IProgressMonitor monitor) {
		try {
			getPlugin().writeInfoMsg("Building frege file: " + file.getName());

			// START_HERE
			//FregeCompiler compiler= new FregeCompiler(PROBLEM_MARKER_ID);
			//compiler.compile(file, monitor);
			// Here we provide a substitute for the compile method that simply
			// runs the parser in place of the compiler but creates problem
			// markers for errors that will show up in the problems view
			runParserForCompiler(file, monitor);

			// doRefresh(file.getParent()); // N.B.: Assumes all generated files go into parent folder
		} catch (Exception e) {
			// catch Exception, because any exception could break the
			// builder infra-structure.
			getPlugin().logException(e.getMessage(), e);
		}
	}

	/**
	 * This is an example "compiler" implementation that simply uses the parse controller
	 * to parse the given file, adding error markers to the file for any parse errors
	 * that are reported.
	 * 
	 * Error markers are created by a special type of message handler (i.e., implementation
	 * of IMessageHandler) known as a MarkerCreator.  The MarkerCreator is passed to the
	 * parse controller.  The parser reports its error messages to the MarkerCreator, and
	 * the MarkerCreator puts corresponding error markers on the file.
	 * 
	 * This example shows the use of two different types of marker creator:  the MarkerCreator
	 * base type and an the MarkerCreatorWithBatching subtype.  In MarkerCreator the error
	 * markers are added to the file one at a time, as error messages are received.  In
	 * MarkerCreatorWithBatching, the information from each error message is cached; 
	 * the corresponding error markers are not created until the flush(..) method is called,
	 * at which point all markers are created together.  MarkerCreatorWithBatching is more
	 * complicated internally and requires proper use of the flush(..) method, but it may
	 * be more efficient at runtime for files that have many errors.  That is because a
	 * Workspace operation is required to add the error markers to the file.  There is one
	 * of these for each of the error markers added in MarkerCreator, but only one for all
	 * of the markers in MarkerCreatorWithBatching.
	 * 
	 * In this example we have declared a marker creator of each type but commented out the
	 * batching version.  The example should also execute correctly if you comment out the
	 * base version and uncomment the batching version, so it should be easy to experiment
	 * with them.
	 * 
	 * TODO remove or rename this method once an actual compiler is being called. 
	 * 
	 * @param file    input source file
	 * @param monitor progress monitor
	 */
	protected void runParserForCompiler(final IFile file,
			final IProgressMonitor monitor) {
		try {
			FregeParseController parseController = new FregeParseController();

			MarkerCreatorWithBatching markerCreator = new MarkerCreatorWithBatching(file, parseController, this);

			parseController.getAnnotationTypeInfo().addProblemMarkerType(
					getErrorMarkerID());

			ISourceProject sourceProject = ModelFactory.open(file.getProject());
			parseController.initialize(file.getProjectRelativePath(),
					sourceProject, markerCreator);

			String contents = BuilderUtils.getFileContents(file);
			final TGlobal result = parseController.parse(contents, monitor);
			if (FregeParseController.errors(result) == 0) {
				// run the eclipse java compiler
				final String target = Box.<String>box(
						FregeParseController.funStG(Main.targetPath(".java"),
								result)).j;
				getPlugin().writeInfoMsg("built: " + target);
				// get the frege path and build path
				final String bp = TOptions.dir( TGlobal.options(result) );
				final TList ourPath = frege.compiler.Utilities.ourPath(TGlobal.options(result));
				final String fp = Box.<String>box(
						Text.joined(
								Box.mk(System.getProperty("path.separator")), 
								ourPath)._e()).j;
				// construct the commandline
				final String cmdline = "-cp " + fp 
						+ " -d " + bp 
						+ " -1.7 -encoding UTF-8 "
						+ target;
				getPlugin().writeInfoMsg("batch-compile " + cmdline);
				class CompProgress extends org.eclipse.jdt.core.compiler.CompilationProgress  {

					@Override
					public void begin(int arg0) { }

					@Override
					public void done() { }

					@Override
					public boolean isCanceled() {
						return monitor.isCanceled();
					}

					@Override
					public void setTaskName(String arg0) {}

					@Override
					public void worked(int arg0, int arg1) { }
					
				}
				final boolean success = org.eclipse.jdt.core.compiler.batch.BatchCompiler.compile(
				   cmdline,
				   new PrintWriter(System.out),
				   new PrintWriter(System.err),
				   new CompProgress());
				if (!success) {
					markerCreator.addMarker(IMarker.SEVERITY_ERROR, 
							"java compiler errors", 
							1, 
							0, 
							1);
				}
			}
			monitor.done();

			if (markerCreator instanceof MarkerCreatorWithBatching) {
				((MarkerCreatorWithBatching) markerCreator).flush(monitor);
			}
		} catch (ModelException | LimitExceededException e) {
			getPlugin()
					.logException(
							"Example builder returns without parsing due to a ModelException",
							e);
		}
	}
}
