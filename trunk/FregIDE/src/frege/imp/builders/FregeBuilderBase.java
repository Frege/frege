
/**
 * This is a rewrite of org.eclipse.imp.builder.BuilderBase
 * with clutter removed not needed for Frege and a dependency
 * management that fits our needs.
 * 
 * The Frege Builder assumes that every Frege Project is a
 * Java Project and that dependencies between Frege sources 
 * are non-cyclic. The latter is guaranteed by the language.
 * 
 * We nevertheless must extend BuilderBase so that we can create MarkerCreatorWithBatching.
 * This is a design flaw in the IMP framework.
 */

package frege.imp.builders;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import frege.FregePlugin;


public abstract class FregeBuilderBase extends BuilderBase {

	public FregeBuilderBase() {}
	
	public static final String LANGUAGE_NAME = FregePlugin.kLanguageID;

	public static final Language LANGUAGE = LanguageRegistry
			.findLanguage(LANGUAGE_NAME);

    
    public final IResourceVisitor fResourceVisitor= new SourceCollectorVisitor();

    protected final IResourceDeltaVisitor fDeltaVisitor= new SourceDeltaVisitor();

    protected IPreferencesService fPrefService;

    public final Set<IFile> fChangedSources= new HashSet<IFile>();

    // protected final Set<IFile> fSourcesToCompile= new HashSet<IFile>();
    // protected final Set<IFile> fSourcesForDeps= new HashSet<IFile>();
    
    protected Map<IFile, Set<IFile>> fDependencies = new HashMap<IFile, Set<IFile>>();
    final protected String fDependencyInfo = "not here";	// make base class item invisible
    
    protected boolean buildStatus = false;
    
	/**
	 * Decide whether a file needs to be build using this builder. 
	 * 
	 * @return true if file has extension <code>.fr</code> and does not live below the output path.
	 */
	@Override public boolean isSourceFile(IFile file) {
		final IPath path = file.getFullPath();
		if (path == null)
			return false;
		final IProject rp     = file.getProject();
		final IJavaProject jp = rp != null ? JavaCore.create(rp) : null;
		try {
			if (jp != null) {
				final IPath bin = jp.getOutputLocation();
				if (bin != null) {
					final IPath rbin = bin.makeRelativeTo(rp.getFullPath());
					final IPath src = path.makeRelativeTo(rp.getFullPath());
					return src != null && rbin != null 
							&& !rbin.isPrefixOf(src) && LANGUAGE.hasExtension(path.getFileExtension());
				}
			}
		}
		catch (JavaModelException e) {
			return false;
		}
		return false;
	}
	
	/**
	 * @return true if this resource identifies the output folder
	 */
	@Override  public boolean isOutputFolder(IResource resource) {
		final IPath path = resource.getFullPath();
		if (path == null)
			return false;
		final IProject rp     = resource.getProject();
		final IJavaProject jp = rp != null ? JavaCore.create(rp) : null;
		try {
			if (jp != null) {
				final IPath bin = jp.getOutputLocation();
				if (bin != null) {
					final IPath rbin = bin.makeRelativeTo(rp.getFullPath());
					final IPath res = path.makeRelativeTo(rp.getFullPath());
					return res != null && rbin != null && rbin.equals(res);
				}
			}
		}
		catch (JavaModelException e) {
			return false;
		}
		return false;
	}


    private final class SourceDeltaVisitor implements IResourceDeltaVisitor {
        @Override
		public boolean visit(IResourceDelta delta) throws CoreException {
            return processResource(delta.getResource());
        }
    }

    private class SourceCollectorVisitor implements IResourceVisitor {
        @Override
		public boolean visit(IResource res) throws CoreException {
            return processResource(res);
        }
    }

    private boolean processResource(IResource resource) {
        if (resource instanceof IFile) {
            IFile file= (IFile) resource;

            if (file.exists()) {
                if (isSourceFile(file) || isNonRootSourceFile(file)) {
                    fChangedSources.add(file);
                }
            }
            return false;
        } else if (isOutputFolder(resource)) {
            return false;
        }
        return true;
    }

    private class AllSourcesVisitor implements IResourceVisitor {
        private final Collection<IFile> fResult;

        public AllSourcesVisitor(Collection<IFile> result) {
            fResult= result;
        }

        @Override
		public boolean visit(IResource resource) throws CoreException {
            if (resource instanceof IFile) {
                IFile file= (IFile) resource;

                if (file.exists()) {
                    if (isSourceFile(file) || isNonRootSourceFile(file)) {
                        fResult.add(file);
                    }
                }
                return false;
            } else if (isOutputFolder(resource)) {
                return false;
            }
            return true;
        }
    }

    
    @Override
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) {
        if (getPreferencesService().getProject() == null) {
            getPreferencesService().setProject(getProject());
        }

        fChangedSources.clear();
        
        boolean haveCompleteDeps = false;
        List<IFile> allSources= new LinkedList<IFile>();

        if (fDependencies.isEmpty() || kind == FULL_BUILD || kind == CLEAN_BUILD) {
            // get all sources
            try {
                getProject().accept(new AllSourcesVisitor(allSources));
            } catch (CoreException e) {
                getPlugin().getLog().log(new Status(IStatus.ERROR, getPlugin().getID(), e.getLocalizedMessage(), e));
            }
            // Collect deps now, so we can compile everything necessary in the case where
            // we have no dep info yet (e.g. first build for this Eclipse invocation --
            // we don't persist the dep info yet) but we've been asked to do an auto build
            // b/c of workspace changes.
            collectDependencies(monitor, allSources);
            haveCompleteDeps = true;
        }

        if (kind == FULL_BUILD || kind == CLEAN_BUILD) {
            clearMarkersOn(allSources);
        }

        try {
            collectSourcesToCompile(monitor);
            if (!haveCompleteDeps)
            	collectDependencies(monitor, fChangedSources);
            compileNecessarySources(monitor);
            monitor.done();
        } catch (CoreException e) {
            getPlugin().writeErrorMsg("Build failed: " + e.getMessage());
        }
        return new IProject[0];
    }

    /**
     * <p> Add to list of sources to compile after files this one depends on that are
     * also in the list of files to compile. </p>
     * @param source the file to add
     * @param acc    the list of files to compile so far
     * @return a new list with maybe some more files added
     */
    private List<IFile> addAfterDeps(IFile source, List<IFile> acc, Collection<IFile> toCompile) {
    	if (acc.contains(source)) return acc;
    	// compile files this one depends on *before* this one
    	// This terminates because Frege dependencies form an acyclic graph
    	
    	Set<IFile> thisDeps = fDependencies.get(source);
    	if (thisDeps == null || thisDeps.isEmpty()) {
    		getPlugin().writeInfoMsg(source.getName() + " has no dependencies (yet).");
    		collectDependencies(source);
    		thisDeps = fDependencies.get(source);
    		if (thisDeps == null) {
        		getPlugin().writeInfoMsg(source.getName() + " has no dependencies at all.");
    		}
    	}
    	if (thisDeps != null) for (IFile depFile : thisDeps) {
    		// IFile depFile = getProject().getWorkspace().getRoot().getFile(new Path(depPath));
    		if (isSourceFile(depFile) && toCompile.contains(depFile)) {
    			acc = addAfterDeps(depFile, acc, toCompile);
    		}
    	}
    	acc = new LinkedList<IFile>(acc);
    	acc.add(source);
    	return acc;
    }
    
    /**
     * Let it be known to the builder that File <code>from</code> depends on <code>to</code>
     * @param from 	the source file that imports to
     * @param to	the file that is imported by from
     */
    public void addDependency(IFile from, IFile to) {
    	if (fDependencies.get(from) == null)
    		fDependencies.put(from, new HashSet<IFile>());
    	fDependencies.get(from).add(to);
    }
    
    /**
     * Checks if a file depends on another one directly or indirectly
     * @param a
     * @param b
     * @return  true, if a depends on b, false otherwise
     */
    protected boolean usesTransitive(IFile a, IFile b) {
    	if (a.equals(b)) return false;
    	final Set<IFile> direct = fDependencies.get(a);
    	if (direct == null) return false;
    	if (direct.contains(b)) return true;
    	for (IFile d : direct)
    		if (usesTransitive(d, b)) return true;
    	return false;
    }
    
    /**
     * Add cc and any known source file that depends on it to ccs
     * 
     * @param cc	the file that will be compiled
     * @param ccs	the accumulated set of files that will be compiled
     */
    protected void addTransitive(IFile cc, Set<IFile> ccs) {
    	if (ccs.contains(cc)) return;					// save duplication of work
    	ccs.add(cc);
    	
    	final Set<IFile> keys = fDependencies.keySet();
    	for (IFile k : keys) {
    		if (fDependencies.get(k).contains(cc)) addTransitive(k, ccs);
    	}
    }
    
    /**
     * Determines the files to compile and compiles them in the correct order.
     * 
     * All files in <code>fChangedSources</code> must be compiled.
     * 
     * All files that depend on compiled files must be compiled.
     * 
     * Once a compilation fails, other files that depend on the failed one will not be compiled.
     */
    @Override
	protected void compileNecessarySources(IProgressMonitor monitor) {
    	Set<IFile> toCompile = new HashSet<IFile>();
    	for (IFile f : fChangedSources)
    		addTransitive(f, toCompile);
    			
    	String show = "";
    	List<IFile> ord = new LinkedList<IFile>();
    	for(IFile src : toCompile) {
    		if (!isSourceFile(src)) continue;
    		if (show.length() > 0) show += ", ";
    		show += src.getName();
    		ord = addAfterDeps(src, ord, toCompile);
    	}
    	getPlugin().writeInfoMsg("Files to build: " + show);
    	
    	show = "";
    	for(IFile src : ord) {
    		if (!isSourceFile(src)) continue;
    		if (show.length() > 0) show += ", ";
    		show += src.getName();
    	}
    	getPlugin().writeInfoMsg("Build order: " + show);
    	
    	monitor.beginTask("compiling " + show, ord.size());
    	
    	Set<IFile> failed = new HashSet<IFile>();
    	
        for(IFile srcFile : ord) {
        	monitor.subTask("building " + srcFile.getName());
            boolean usesFailed = false;
            for (IFile f: failed) {
            	if (usesTransitive(srcFile, f)) {
            		usesFailed = true;
            		break;
            	}
            }
            if (!usesFailed) {
            	clearMarkersOn(srcFile);   
            	if (!compiled(srcFile, monitor)) {
            		failed.add(srcFile);
            	}
            }
            monitor.worked(1);
        }
        
        monitor.done();
    }

    abstract boolean compiled(IFile srcFile, IProgressMonitor monitor);

	protected void collectDependencies(IProgressMonitor monitor, Collection<IFile> sources) {
        for(IFile srcFile: sources) {
            collectDependencies(srcFile);
            if (monitor.isCanceled()) break;
        }
    }
    

    /**
     * Visits the project delta, if any, or the entire project, and determines the set
     * of files needed recompilation, and adds them to <code>fChangedSources</code>.
     * @param monitor
     * @throws CoreException
     */
    private void collectSourcesToCompile(IProgressMonitor monitor) throws CoreException {
        IResourceDelta delta= getDelta(getProject());
        boolean emitDiags= getDiagPreference();

        if (delta != null) {
            delta.accept(fDeltaVisitor);
            for (IFile chg : fChangedSources) {
            	fDependencies.put(chg, new HashSet<IFile>());	// clear dependencies of changed files
            }
        	collectDependencies(monitor, fChangedSources);		// re-collect dependencies of changed files
        } else {
            getProject().accept(fResourceVisitor);
        }
    }

}
