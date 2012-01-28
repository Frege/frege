package frege.imp.actions;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import frege.FregePlugin;
import frege.imp.builders.FregeNature;
import frege.imp.preferences.FregePreferencesConstants;

public class EnableFregeNature implements IWorkbenchWindowActionDelegate {
	private IProject fProject;

	public EnableFregeNature() {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	public void run(IAction action) {
		boolean isJava = false;
		new FregeNature().addToProject(fProject);
		try {
			isJava = fProject.hasNature("org.eclipse.jdt.core.javanature");
			
		} catch (CoreException e) {
			// remain silent in actions
		}

		if (isJava) {
			IJavaProject jp = JavaCore.create(fProject);
			try {
				IClasspathEntry[] old = jp.getRawClasspath();
				IClasspathEntry[] cpe = new  IClasspathEntry[old.length+1];
				int i = 0;
				for (i=0; i<old.length; i++) {
					cpe[i] = old[i];
				}
				IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
				String flib = service.getStringPreference(FregePreferencesConstants.P_FREGELIB);
				IPath path = new Path(flib);				
				cpe[i] = JavaCore.newLibraryEntry(path, null, null);
				jp.setRawClasspath(cpe, null);
			} catch (JavaModelException e) {
				// leck mich
				// e.printStackTrace();
			}
		}
		else {			
			// show error message
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object first = ss.getFirstElement();

			if (first instanceof IJavaProject) {
				fProject = ((IJavaProject) first).getProject();
			} else if (first instanceof IProject) {
				fProject = (IProject) first;
			}
			else if (first == null) return;
			// Cannot set frege nature on non java project.
			// Only a java project gives us a build path and run configurations. 
			try {
				action.setEnabled(fProject.hasNature("org.eclipse.jdt.core.javanature"));
			} catch (CoreException e) {
				action.setEnabled(true);
			}
		}
	}
}
