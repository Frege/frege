/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package frege.imp.preferences;

import org.eclipse.imp.preferences.PreferencesInitializer;
import org.eclipse.imp.preferences.IPreferencesService;
import frege.FregePlugin;

/**
 * Initializations of default values for preferences.
 */
public class FregeInitializer extends PreferencesInitializer {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();

		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregeConstants.P_SOURCEFONT, "Courier New");
		service.setBooleanPreference(IPreferencesService.DEFAULT_LEVEL, FregeConstants.P_WARNINGSON, true);
		service.setBooleanPreference(IPreferencesService.DEFAULT_LEVEL, FregeConstants.P_USEDEFAULTIMPORTPATH, true);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregeConstants.P_IMPORTPATHTOUSE, ".;..;${project}");
	}

	/*
	 * Clear (remove) any preferences set on the given level.
	 */
	public void clearPreferencesOnLevel(String level) {
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		service.clearPreferencesAtLevel(level);

	}
}
