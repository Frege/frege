package frege.imp.tree;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.services.ILabelProvider;
import org.eclipse.imp.language.ILanguageService;
import frege.FregePlugin;
import frege.IFregeResources;
import org.eclipse.imp.utils.MarkerUtils;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;



public class FregeLabelProvider implements ILabelProvider {
	private Set<ILabelProviderListener> fListeners = new HashSet<ILabelProviderListener>();

	private static ImageRegistry sImageRegistry = FregePlugin.getInstance()
			.getImageRegistry();

	private static Image DEFAULT_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_DEFAULT_IMAGE);
	private static Image FILE_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE);
	private static Image FILE_WITH_WARNING_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE_WARNING);
	private static Image FILE_WITH_ERROR_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE_ERROR);

	public Image getImage(Object element) {
		if (element instanceof IFile) {
			// TODO:  rewrite to provide more appropriate images
			IFile file = (IFile) element;
			int sev = MarkerUtils.getMaxProblemMarkerSeverity(file,
					IResource.DEPTH_ONE);

			switch (sev) {
			case IMarker.SEVERITY_ERROR:
				return FILE_WITH_ERROR_IMAGE;
			case IMarker.SEVERITY_WARNING:
				return FILE_WITH_WARNING_IMAGE;
			default:
				return FILE_IMAGE;
			}
		}
		/*
		ASTNode n = (element instanceof ModelTreeNode) ? (ASTNode) ((ModelTreeNode) element)
				.getASTNode() : (ASTNode) element;
				*/
		return getImageFor(element);
		
	}

	public static Image getImageFor(Object n) {
		// TODO:  return specific images for specific node
		// types, as images are available and appropriate
		return DEFAULT_IMAGE;
	}

	public String getText(Object element) {
		Object n = (element instanceof ModelTreeNode)  
				? ((ModelTreeNode) element).getASTNode() 
			    : element;

		return getLabelFor(n);
	}

	public static String getLabelFor(Object n) {
		// START_HERE
//		if (n instanceof IcompilationUnit)
//			return "Compilation unit";
//		if (n instanceof block)
//			return "Block";
//		if (n instanceof assignmentStmt) {
//			assignmentStmt stmt = (assignmentStmt) n;
//			return stmt.getidentifier().toString() + "="
//					+ stmt.getexpression().toString();
//		}
//		if (n instanceof declarationStmt0) {
//			declaration decl = (declaration) ((declarationStmt0) n)
//					.getdeclaration();
//			return decl.getprimitiveType() + " "
//					+ decl.getidentifier().toString();
//		}
//		if (n instanceof declarationStmt1) {
//			declaration decl = (declaration) ((declarationStmt1) n)
//					.getdeclaration();
//			return decl.getprimitiveType() + " "
//					+ decl.getidentifier().toString();
//		}
//		if (n instanceof functionDeclaration) {
//			functionHeader hdr = (functionHeader) ((functionDeclaration) n)
//					.getfunctionHeader();
//			return hdr.getidentifier().toString();
//		}
		return "instance of (" + n.getClass().getName() + ")";
	}

	public void addListener(ILabelProviderListener listener) {
		fListeners.add(listener);
	}

	public void dispose() {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		fListeners.remove(listener);
	}
}
