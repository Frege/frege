package frege.imp.builders;

import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;

import frege.FregePlugin;

public class FregeNature extends ProjectNatureBase {
	public FregeNature() { super(); }
	public static final String k_natureID = FregePlugin.kPluginID
			+ ".frege.nature";

	public String getNatureID() {
		return k_natureID;
	}

	public String getBuilderID() {
		return FregeBuilder.BUILDER_ID;
	}

	public void refreshPrefs() {
		// TODO implement preferences and hook in here
	}

	public IPluginLog getLog() {
		return FregePlugin.getInstance();
	}

	public String getDownstreamBuilderID() {
		// There is no builder that will consume artifacts created by this nature's builder,
		// hence null
		return null;
	}
}
