/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package frege.imp.preferences;

import org.eclipse.swt.widgets.TabFolder;import org.eclipse.imp.preferences.IPreferencesService;import org.eclipse.imp.preferences.PreferencesInitializer;import org.eclipse.imp.preferences.PreferencesTab;import org.eclipse.imp.preferences.TabbedPreferencesPage;import frege.FregePlugin;

/**
 * A preference page class.
 */
public class FregePreferencePage extends TabbedPreferencesPage {
	public FregePreferencePage() {
		super();
		prefService = FregePlugin.getInstance().getPreferencesService();
	}

	protected PreferencesTab[] createTabs(IPreferencesService prefService,
		TabbedPreferencesPage page, TabFolder tabFolder) {
		PreferencesTab[] tabs = new PreferencesTab[2];

		FregeInstanceTab instanceTab = new FregeInstanceTab(prefService);
		instanceTab.createTabContents(page, tabFolder);
		tabs[0] = instanceTab;

		FregeProjectTab projectTab = new FregeProjectTab(prefService);
		projectTab.createTabContents(page, tabFolder);
		tabs[1] = projectTab;

		return tabs;
	}

	public PreferencesInitializer getPreferenceInitializer() {
		return new FregeInitializer();
	}
}
