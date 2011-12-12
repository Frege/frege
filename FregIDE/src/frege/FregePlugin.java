package frege;

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
