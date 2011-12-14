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
		URL url = this.getBundle().getEntry(".");
		System.err.println(kPluginID + ": " + url);
		URL fileUrl = FileLocator.toFileURL(url);
		System.err.println(kPluginID + ": " + fileUrl);
		final ProtectionDomain pd = this.getClass().getProtectionDomain();
		final CodeSource cs = pd.getCodeSource();
		URL xurl = cs.getLocation();
		System.err.println(kPluginID + ": " + xurl);
	}

	@Override
	public String getID() {
		return kPluginID;
	}

	@Override
	public String getLanguageID() {
		return kLanguageID;
	}
}
