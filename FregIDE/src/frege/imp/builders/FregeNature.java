package frege.imp.builders;

import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;

import frege.FregePlugin;

public class FregeNature extends ProjectNatureBase {
	public FregeNature() { super(); }
	public static final String k_natureID = FregePlugin.kPluginID
			+ ".imp.nature";

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
		// TODO If needed, specify the builder that will consume artifacts created by this nature's builder, or null if none
		return "org.eclipse.jdt.core.javabuilder";
	}
}
