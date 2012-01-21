package frege.imp.builders;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

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
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;


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
import frege.prelude.Base.TList.DCons;
import frege.prelude.Text;
import frege.rt.Box;

/**
 * A builder may be activated on a file containing frege code every time it
 * has changed (when "Build automatically" is on), or when the programmer
 * chooses to "Build" a project.
 * 
 */
public class FregeBuilder extends FregeBuilderBase {
	
	public FregeBuilder() {}

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
	 * Collects compilation-unit dependencies for the given file, and records
	 * them via calls to <code>fDependency.addDependency()</code>.
	 */
	@Override public void collectDependencies(IFile file) {
		
		try {
			final ISourceProject sourceProject 
				= ModelFactory.open(file.getProject());
			final String fromPath = file.getFullPath().toString();
			final FregeParseController.FregeData fd 
				= new FregeParseController.FregeData(sourceProject);
			final String[] srcs = fd.getSp().split(System.getProperty("path.separator"));
			final String contents = BuilderUtils.getFileContents(file);
			TList packs = (TList) frege.compiler.Scanner.dependencies(Box.mk(contents))._e();
			getPlugin().writeInfoMsg(
					"Collecting dependencies from frege file: " + fromPath);
			while (true) {
				final DCons cons = packs._Cons();
				if (cons == null) break;
				packs = (TList) cons.mem2._e();
				final String pack = Box.<String>box(cons.mem1._e()).j;
				final String fr = pack.replaceAll("\\.", "/") + ".fr";
				for (String sf: srcs) {
					final IPath p = new Path(sf + "/" + fr);
					// getProject().getWorkspace().getRoot().getFile(new Path(depPath));
					final IResource toRes = file.getProject().findMember(p);  
					getPlugin().writeInfoMsg(
							"DependenciesCollector looks for: " + p.toPortableString());
					if (toRes != null && toRes instanceof IFile) {
						final IFile to = (IFile) toRes;
						// avoid endless recursion for frege.prelude.Base -> frege.prelude.Base
						if (!file.equals(to))	{ 
							this.addDependency(file, to);
							getPlugin().writeInfoMsg(
									"DependenciesCollector found: " + to.getFullPath());
						}
						break;
					}
				}
			}
		}
		catch (ModelException mex) {
			getPlugin().writeErrorMsg(
					"Can't get source project: " + mex.getMessage());
			return;
		}
	}



	/**
	 * Compile one frege file.
	 */
	@Override public boolean compiled(final IFile file, IProgressMonitor monitor) {
		boolean succ = false;
		try {
			getPlugin().writeInfoMsg("Building frege file: " + file.getName());
			succ = runParserForCompiler(file, monitor);
			// do wee need that?
			// doRefresh(file.getProject());
		} catch (Exception e) {
			// catch Exception, because any exception could break the
			// builder infrastructure.
			getPlugin().logException(e.getMessage(), e);
		}
		return succ;
	}
	@Override public void compile(IFile f, IProgressMonitor m) { compiled(f, m); }
	

	/**
	 * This is a compiler implementation that simply uses the parse controller
	 * to parse the given file, adding markers to the file for any errors,
	 * warnings or hints that are reported.
	 * 
	 * Error markers are created by a special type of message handler (i.e., implementation
	 * of IMessageHandler) known as a MarkerCreator.  The MarkerCreator is passed to the
	 * parse controller.  The parser reports its error messages to the MarkerCreator, and
	 * the MarkerCreator puts corresponding error markers on the file.
	 *   
	 * 
	 * @param file    input source file
	 * @param monitor progress monitor
	 */
	protected boolean runParserForCompiler(final IFile file,
			final IProgressMonitor monitor) {
		// a class we can give the compiler as progress monitor
		class CompProgress extends org.eclipse.jdt.core.compiler.CompilationProgress implements IProgressMonitor  {

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

			@Override
			public void beginTask(String name, int totalWork) {
				
			}

			@Override
			public void internalWorked(double work) {
				monitor.internalWorked(work);
			}

			@Override
			public void setCanceled(boolean value) {
				monitor.setCanceled(value);
			}

			@Override
			public void subTask(String name) {
				monitor.subTask(name);
			}

			@Override
			public void worked(int work) {
			}
			
		}
		
		boolean success = false;
		try {
			FregeParseController parseController = new FregeParseController();

			MarkerCreatorWithBatching markerCreator = new MarkerCreatorWithBatching(file, parseController, this);

			parseController.getAnnotationTypeInfo().addProblemMarkerType(
					getErrorMarkerID());

			ISourceProject sourceProject = ModelFactory.open(file.getProject());
			parseController.initialize(file.getProjectRelativePath(),
					sourceProject, markerCreator);

			String contents = BuilderUtils.getFileContents(file);
			final TGlobal result = parseController.parse(contents, new CompProgress());
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
				final String cmdline = "-cp " + "\"" + fp + "\"" 
						+ " -d " + "\"" + bp + "\"" 
						+ " -Xemacs -1.7 -encoding UTF-8 "
						+ "\"" + target + "\"";
				getPlugin().writeInfoMsg("batch-compile " + cmdline);
				final StringWriter errs = new StringWriter();
				success = org.eclipse.jdt.core.compiler.batch.BatchCompiler.compile(
				   cmdline,
				   new PrintWriter(System.out),
				   new PrintWriter(errs),
				   new CompProgress());
				
				/* write java error messages to some file.
				if (errs.toString().length() > 0) try {
					FileWriter f = new FileWriter(target + ".errors");
					f.append(errs.toString());
					f.close();
				}
				catch (IOException e) {
					getPlugin().writeErrorMsg("could not write java compiler errors " + e.getMessage());
				}
				*/
				
				if (!success) {
					TPosition pos = (TPosition) TPosition.packageStart(result)._e();
					TToken module = TPosition.first(pos);
					int line = TToken.line(module);
					int chStart = TToken.offset(module);
					int chEnd = chStart + TToken.length(module);
					String msg = errs.toString();
					String[] msgs = msg.split(System.getProperty("line.terminator", "\n"));
					Pattern p = Pattern.compile(":\\d+:\\s+error:(.*)");
					for (String s : msgs) {
						if (s == null) continue;
						// getPlugin().writeInfoMsg(s);
						Matcher m = p.matcher(s);
						if (m.find()) {
							String se = m.group(1);
							markerCreator.addMarker(
									IMarker.SEVERITY_ERROR, 
									se, line, chStart, chEnd);
						}
					}
					getPlugin().writeErrorMsg("java compiler failed on " + target);
					markerCreator.addMarker(IMarker.SEVERITY_INFO, 
							"Java compiler errors are almost always caused by bad native declarations. "
								+ "When you're sure this is out of the question you've found a compiler bug, "
								+ "please report under http://code.google.com/p/frege/issues/list and attach a copy of "
								+ target, 
							line, chStart, chEnd);
				}
			}

			if (markerCreator instanceof MarkerCreatorWithBatching) {
				((MarkerCreatorWithBatching) markerCreator).flush(monitor);
			}
		} catch (ModelException e) {
			getPlugin().logException(
							"Example builder returns without parsing due to a ModelException",
							e);
		} catch (LimitExceededException e) {
			getPlugin().logException(
					"Caught exception while building: ",
					e);
		}
		return success;
	}

	/**
	 * Always false, there is not such thing in Frege
	 */
	@Override
	protected boolean isNonRootSourceFile(IFile file) {
		return false;
	}

}
