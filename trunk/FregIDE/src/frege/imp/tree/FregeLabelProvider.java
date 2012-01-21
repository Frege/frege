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
import frege.compiler.Data.TPack;

import org.eclipse.imp.utils.MarkerUtils;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;



public class FregeLabelProvider implements ILabelProvider {
	private Set<ILabelProviderListener> fListeners = new HashSet<ILabelProviderListener>();

	private static ImageRegistry sImageRegistry = FregePlugin.getInstance()
			.getImageRegistry();

	final public static Image DEFAULT_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_DEFAULT_IMAGE);
	final public static Image OUTLINE_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_DEFAULT_OUTLINE_ITEM);
	final public static Image PACKAGE_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_PACKAGE_OUTLINE_ITEM);
	final public static Image IMPORT_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_IMPORT_OUTLINE_ITEM);
	final public static Image TYPE_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_TYPE_OUTLINE_ITEM);
	final public static Image CLASS_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_CLASS_OUTLINE_ITEM);
	final public static Image INST_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_INST_OUTLINE_ITEM);
	final public static Image DATA_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_DATA_OUTLINE_ITEM);
	final public static Image CON_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_CON_OUTLINE_ITEM);
	final public static Image VAR_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_VAR_OUTLINE_ITEM);
	final public static Image LINK_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_LINK_OUTLINE_ITEM);
	final public static Image[] SYMBOL_IMAGES = new Image[] { 
		DATA_IMAGE, LINK_IMAGE, CON_IMAGE, CLASS_IMAGE, INST_IMAGE, VAR_IMAGE, TYPE_IMAGE  
		};
	final public static Image FILE_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE);
	final public static Image FILE_WITH_WARNING_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE_WARNING);
	final public static Image FILE_WITH_ERROR_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE_ERROR);
	final public static Image FILE_WITH_INFO_IMAGE = sImageRegistry
			.get(IFregeResources.FREGE_FILE_INFO);

	public Image getImage(Object element) {
		if (element instanceof IFile) {
			
			IFile file = (IFile) element;
			int sev = MarkerUtils.getMaxProblemMarkerSeverity(file,
					IResource.DEPTH_ONE);

			switch (sev) {
			case IMarker.SEVERITY_ERROR:
				return FILE_WITH_ERROR_IMAGE;
			case IMarker.SEVERITY_WARNING:
				return FILE_WITH_WARNING_IMAGE;
//			case IMarker.SEVERITY_INFO:
//				return FILE_WITH_INFO_IMAGE;
			default:
				return FILE_IMAGE;
			}
		}
		Object n = (element instanceof ModelTreeNode) 
				? ((ModelTreeNode) element).getASTNode() 
				: element;
				
		return getImageFor(n);
		
	}

	public static Image getImageFor(Object n) {
		if (n instanceof ITreeItem)
			return ((ITreeItem) n).getImage();
		return OUTLINE_IMAGE;
	}

	public String getText(Object element) {
		Object n = (element instanceof ModelTreeNode)  
				? ((ModelTreeNode) element).getASTNode() 
			    : element;

		return getLabelFor(n);
	}

	public static String getLabelFor(Object n) {
		if (n instanceof ITreeItem)
			return ((ITreeItem) n).getLabel();
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
