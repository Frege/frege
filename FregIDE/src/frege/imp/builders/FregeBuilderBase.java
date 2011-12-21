/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

/**
 * This is essentially org.eclipse.imp.builder.BuilderBase
 * except for compileNecessarySources(), which needs to bring
 * the files in the correct order.
 * It would have been fine to implement this in FregeBuilder,
 * but they work with private variables in BuilderBase. No way to get there.
 * Such are the blessings of OOP :)
 */

package frege.imp.builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.builder.DependencyInfo;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.imp.preferences.PreferenceConstants;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.imp.runtime.RuntimePlugin;
import org.eclipse.imp.utils.UnimplementedError;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


public abstract class FregeBuilderBase extends BuilderBase {
    
    protected final IResourceVisitor fResourceVisitor= new SourceCollectorVisitor();

    protected final IResourceDeltaVisitor fDeltaVisitor= new SourceDeltaVisitor();

    protected IPreferencesService fPrefService;

    protected final Collection<IFile> fChangedSources= new HashSet<IFile>();

    protected final Collection<IFile> fSourcesToCompile= new HashSet<IFile>();

    protected final Collection<IFile> fSourcesForDeps= new HashSet<IFile>();

    private final class SourceDeltaVisitor implements IResourceDeltaVisitor {
        public boolean visit(IResourceDelta delta) throws CoreException {
            return processResource(delta.getResource());
        }
    }

    private class SourceCollectorVisitor implements IResourceVisitor {
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

    @SuppressWarnings("unchecked")
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor) {
        if (getPreferencesService().getProject() == null) {
            getPreferencesService().setProject(getProject());
        }

        fChangedSources.clear();
        fSourcesForDeps.clear();
        fSourcesToCompile.clear();

        boolean partialDeps= true;
        Collection<IFile> allSources= new ArrayList<IFile>();

        if (fDependencyInfo == null || kind == FULL_BUILD || kind == CLEAN_BUILD) {
            fDependencyInfo= createDependencyInfo(getProject());
            try {
                getProject().accept(new AllSourcesVisitor(allSources));
            } catch (CoreException e) {
                getPlugin().getLog().log(new Status(IStatus.ERROR, getPlugin().getID(), e.getLocalizedMessage(), e));
            }
            fSourcesForDeps.addAll(allSources);
            // Collect deps now, so we can compile everything necessary in the case where
            // we have no dep info yet (e.g. first build for this Eclipse invocation --
            // we don't persist the dep info yet) but we've been asked to do an auto build
            // b/c of workspace changes.
            collectDependencies(monitor);
            partialDeps= false;
        }

        if (kind == FULL_BUILD || kind == CLEAN_BUILD) {
            clearMarkersOn(allSources);
        }

        try {
            collectSourcesToCompile(monitor);
            clearDependencyInfoForChangedFiles();
            if (partialDeps) {
                fSourcesForDeps.addAll(fSourcesToCompile);
                fSourcesForDeps.addAll(fChangedSources);
                collectDependencies(monitor);
            }
            compileNecessarySources(monitor);
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
    private List<IFile> addAfterDeps(IFile source, List<IFile> acc, IProgressMonitor monitor) {
    	if (acc.contains(source)) return acc;
    	// compile files this one depends on *before* this one
    	// This terminates because Frege dependencies form an acyclic graph
    	
    	Set<String> thisDeps = fDependencyInfo.getDependencies().get(source.getFullPath().toString());
    	if (thisDeps == null || thisDeps.isEmpty()) {
    		getPlugin().writeInfoMsg(source.getName() + " has no dependencies (yet).");
    		collectDependencies(source);
    		thisDeps = fDependencyInfo.getDependencies().get(source.getFullPath().toString());
    		if (thisDeps == null) {
        		getPlugin().writeInfoMsg(source.getName() + " has no dependencies at all.");
    		}
    	}
    	if (thisDeps != null) for (String depPath : thisDeps) {
    		IFile depFile = getProject().getWorkspace().getRoot().getFile(new Path(depPath));
    		if (isSourceFile(depFile) && fSourcesToCompile.contains(depFile)) {
    			acc = addAfterDeps(depFile, acc, monitor);
    		}
    	}
    	acc = new LinkedList<IFile>(acc);
    	acc.add(source);
    	return acc;
    }
    
    protected void compileNecessarySources(IProgressMonitor monitor) {
    	String show = "";
    	List<IFile> ord = new LinkedList<IFile>();
    	for(IFile src : fSourcesToCompile) {
    		if (!isSourceFile(src)) continue;
    		if (show.length() > 0) show += ", ";
    		show += src.getName();
    		ord = addAfterDeps(src, ord, monitor);
    	}
    	getPlugin().writeInfoMsg("Files to build: " + show);
    	
    	show = "";
    	for(IFile src : ord) {
    		if (!isSourceFile(src)) continue;
    		if (show.length() > 0) show += ", ";
    		show += src.getName();
    		
    	}
    	getPlugin().writeInfoMsg("Build order: " + show);
    	
        for(IFile srcFile : ord) {
            clearMarkersOn(srcFile);
            if (isSourceFile(srcFile)) {
                compile(srcFile, monitor);
            }
        }
    }

    protected void collectDependencies(IProgressMonitor monitor) {
        for(IFile srcFile: fSourcesForDeps) {
            collectDependencies(srcFile);
        }
    }
    
    /**
     * Clears the dependency information maintained for all files marked as
     * having changed (i.e. in <code>fSourcesToCompile</code>).
     */
    private void clearDependencyInfoForChangedFiles() {
        for(Iterator<IFile> iter= fSourcesToCompile.iterator(); iter.hasNext(); ) {
            IFile srcFile= iter.next();

            fDependencyInfo.clearDependenciesOf(srcFile.getFullPath().toString());
        }
    }


    /**
     * Visits the project delta, if any, or the entire project, and determines the set
     * of files needed recompilation, and adds them to <code>fSourcesToCompile</code>.
     * @param monitor
     * @throws CoreException
     */
    private void collectSourcesToCompile(IProgressMonitor monitor) throws CoreException {
        IResourceDelta delta= getDelta(getProject());
        boolean emitDiags= getDiagPreference();

        if (delta != null) {
            delta.accept(fDeltaVisitor);
        } else {
            getProject().accept(fResourceVisitor);
        }
        collectChangeDependents();
    }

    // TODO This really *shouldn't* be transitive; the real problem w/ the LPGBuilder is that it
    // doesn't account for transitive includes itself when computing its dependency info. That is,
    // when file A includes B includes C, A should be marked as a dependent of C.
    private void collectChangeDependents() {
        if (fChangedSources.size() == 0) return;

        Collection<IFile> changeDependents= new HashSet<IFile>();
        boolean emitDiags= getDiagPreference();

        changeDependents.addAll(fChangedSources);

        boolean changed= false;
        do {
            Collection<IFile> additions= new HashSet<IFile>();
            scanSourceList(changeDependents, additions);
            changed= changeDependents.addAll(additions);
        } while (changed);

        for(IFile f: changeDependents) {
            if (isSourceFile(f)) {
                fSourcesToCompile.add(f);
            }
        }
//      getConsoleStream().println("Changed files + dependents:");
//      dumpSourceList(fSourcesToCompile);
    }

    private boolean scanSourceList(Collection<IFile> srcList, Collection<IFile> changeDependents) {
        boolean result= false;
        for(Iterator<IFile> iter= srcList.iterator(); iter.hasNext(); ) {
            IFile srcFile= iter.next();
            Set<String> fileDependents= fDependencyInfo.getDependentsOf(srcFile.getFullPath().toString());

            if (fileDependents != null) {
                for(Iterator<String> iterator= fileDependents.iterator(); iterator.hasNext(); ) {
                    String depPath= iterator.next();
                    IFile depFile= getProject().getWorkspace().getRoot().getFile(new Path(depPath));

                    result= result || changeDependents.add(depFile);
                }
            }
        }
        return result;
    }

}
