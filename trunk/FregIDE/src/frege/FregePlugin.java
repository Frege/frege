package frege;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

public class FregePlugin extends PluginBase {

	public static final String kPluginID = "frege.ide";
	public static final String kLanguageID = "frege";
	private static String fregeLib = null;

	/**
	 * The unique instance of this plugin class
	 */
	protected static FregePlugin sPlugin;

	public static FregePlugin getInstance() {
		if (sPlugin == null)
			new FregePlugin();
		return sPlugin;
	}

	public FregePlugin() {
		super();
		sPlugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * get the path name of the Frege Standard Library
	 */
	public String getFregeLib() {
		if (fregeLib == null) {
			final ProtectionDomain pd = this.getClass().getProtectionDomain();
			final CodeSource cs = pd.getCodeSource();
			URL xurl = cs.getLocation();
			fregeLib = xurl.getPath();
			System.err.println(kPluginID + ": " + xurl);
		}
		return fregeLib;
	}

	@Override
	public String getID() {
		return kPluginID;
	}

	@Override
	public String getLanguageID() {
		return kLanguageID;
	}

	// Definitions for image management

	public static final org.eclipse.core.runtime.IPath ICONS_PATH = new org.eclipse.core.runtime.Path(
			"icons/"); //$NON-NLS-1$("icons/"); //$NON-NLS-1$

	protected void initializeImageRegistry(
			org.eclipse.jface.resource.ImageRegistry reg) {
		org.osgi.framework.Bundle bundle = getBundle();
		org.eclipse.core.runtime.IPath path = ICONS_PATH
				.append("tag_outline.png");//$NON-NLS-1$
		org.eclipse.jface.resource.ImageDescriptor imageDescriptor = createImageDescriptor(
				bundle, path);
		reg.put(IFregeResources.FREGE_DEFAULT_IMAGE, imageDescriptor);

		path = ICONS_PATH.append("tag_outline.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_DEFAULT_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("package.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_PACKAGE_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("import.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_IMPORT_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("tag_pink.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_TYPE_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("tag_purple.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_CLASS_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("tag_orange.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_INST_OUTLINE_ITEM, imageDescriptor);

		path = ICONS_PATH.append("tag_red.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_DATA_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("tag_yellow.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_CON_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("tag_green.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_VAR_OUTLINE_ITEM, imageDescriptor);
		
		path = ICONS_PATH.append("tag.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_LINK_OUTLINE_ITEM, imageDescriptor);
		
		// path = ICONS_PATH.append("frege_file.gif");//$NON-NLS-1$
		path = ICONS_PATH.append("page_green.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE, imageDescriptor);

		path = ICONS_PATH.append("page_warning.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE_WARNING, imageDescriptor);

		path = ICONS_PATH.append("page_info.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE_INFO, imageDescriptor);
		
		path = ICONS_PATH.append("page_red.png");//$NON-NLS-1$
		imageDescriptor = createImageDescriptor(bundle, path);
		reg.put(IFregeResources.FREGE_FILE_ERROR, imageDescriptor);
	}

	public static org.eclipse.jface.resource.ImageDescriptor createImageDescriptor(
			org.osgi.framework.Bundle bundle,
			org.eclipse.core.runtime.IPath path) {
		java.net.URL url = org.eclipse.core.runtime.FileLocator.find(bundle,
				path, null);
		if (url != null) {
			return org.eclipse.jface.resource.ImageDescriptor
					.createFromURL(url);
		}
		return null;
	}

	// Definitions for image management end

}
