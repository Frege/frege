/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package frege.imp.preferences;

import org.eclipse.imp.preferences.PreferencesInitializer;
import org.eclipse.imp.preferences.IPreferencesService;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import frege.FregePlugin;

/**
 * Initializations of default values for preferences.
 */
public class FregePreferencesInitializer extends PreferencesInitializer {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		Display display = Display.getDefault();
		String darkYellow = StringConverter.asString(display.getSystemColor(SWT.COLOR_DARK_YELLOW).getRGB());
		String darkRed    = StringConverter.asString(display.getSystemColor(SWT.COLOR_DARK_RED).getRGB());
		String black      = StringConverter.asString(display.getSystemColor(SWT.COLOR_BLACK).getRGB());
		String red        = StringConverter.asString(display.getSystemColor(SWT.COLOR_RED).getRGB());
		String blue       = StringConverter.asString(display.getSystemColor(SWT.COLOR_BLUE).getRGB());
		String darkBlue   = StringConverter.asString(display.getSystemColor(SWT.COLOR_DARK_BLUE).getRGB());
		String darkMagenta= StringConverter.asString(display.getSystemColor(SWT.COLOR_DARK_MAGENTA).getRGB());
		String darkGreen  = StringConverter.asString(display.getSystemColor(SWT.COLOR_DARK_GREEN).getRGB());
		String darkCyan   = StringConverter.asString(display.getSystemColor(SWT.COLOR_DARK_CYAN).getRGB());

		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_SOURCEFONT, "Consolas");
		// service.setIntPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_TABWIDTH, 4);
		// service.setBooleanPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_SPACESFORTABS, true);
		service.setBooleanPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_ITALICIMPORTS, true);
		service.setBooleanPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_BOLDNS, true);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_DOCUCOLOR, darkBlue);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_COMMCOLOR, darkCyan);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_TCONCOLOR, darkRed);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_DCONCOLOR, darkYellow);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_VARIDCOLOR, black);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_IMPORTCOLOR, black);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_KEYWORDCOLOR, darkMagenta);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_SPECIALCOLOR, darkMagenta);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_LITERALCOLOR, blue);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_ERRORCOLOR, red);
		service.setStringPreference(IPreferencesService.DEFAULT_LEVEL, FregePreferencesConstants.P_FREGELIB, FregePlugin.getInstance().getFregeLib());
		
		/*
		System.err.println("darkYellow is " + darkYellow);
		System.err.println("darkRed is " + darkRed);
		System.err.println("black is " + black);
		System.err.println("red is " + red);
		System.err.println("darkMagenta is " + darkMagenta);
		System.err.println("darkGreen is " + darkGreen);
		System.err.println("darkCyan is " + darkCyan);
		*/
	}

	/*
	 * Clear (remove) any preferences set on the given level.
	 */
	public void clearPreferencesOnLevel(String level) {
		IPreferencesService service = FregePlugin.getInstance().getPreferencesService();
		service.clearPreferencesAtLevel(level);

	}
}
