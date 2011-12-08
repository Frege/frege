/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package frege.imp.preferences;

import java.util.List;
import java.util.ArrayList;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Group;
import org.eclipse.imp.preferences.*;
import org.eclipse.imp.preferences.fields.*;
import org.osgi.service.prefs.Preferences;


/**
 * The instance level preferences tab.
 */
public class FregeInstancePreferencesTab extends InstancePreferencesTab {

	public FregeInstancePreferencesTab(IPreferencesService prefService) {
		super(prefService, false);
	}

	/**
	 * Creates specific preference fields with settings appropriate to
	 * the instance preferences level.
	 *
	 * Overrides an unimplemented method in PreferencesTab.
	 *
	 * @return    An array that contains the created preference fields
	 *
	 */
	protected FieldEditor[] createFields(TabbedPreferencesPage page, Composite parent)
	{
		List<FieldEditor> fields = new ArrayList<FieldEditor>();

		FontFieldEditor sourceFont = fPrefUtils.makeNewFontField(
			page, this, fPrefService,
			"instance", "sourceFont", "Editor Font",
			"",
			parent,
			true, true,
			false);
		fields.add(sourceFont);

		Link sourceFontDetailsLink = fPrefUtils.createDetailsLink(parent, sourceFont, sourceFont.getChangeControl().getParent(), "Details ...");

		sourceFontDetailsLink.setEnabled(true);
		fDetailsLinks.add(sourceFontDetailsLink);


		IntegerFieldEditor tabWidth = fPrefUtils.makeNewIntegerField(
			page, this, fPrefService,
			"instance", "tabWidth", "Tabulator Width",
			"",
			parent,
			true, true,
			true, "0",
			false);
		fields.add(tabWidth);

		Link tabWidthDetailsLink = fPrefUtils.createDetailsLink(parent, tabWidth, tabWidth.getTextControl().getParent(), "Details ...");

		tabWidthDetailsLink.setEnabled(true);
		fDetailsLinks.add(tabWidthDetailsLink);


		BooleanFieldEditor spacesForTabs = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "spacesForTabs", "Tab inserts spaces",
			"Is it strongly suggested to not have tabulator characters in Frege source code.",
			parent,
			true, true,
			true, false,
			false);
		fields.add(spacesForTabs);

		Link spacesForTabsDetailsLink = fPrefUtils.createDetailsLink(parent, spacesForTabs, spacesForTabs.getChangeControl().getParent(), "Details ...");

		spacesForTabsDetailsLink.setEnabled(true);
		fDetailsLinks.add(spacesForTabsDetailsLink);

		ColorFieldEditor docuColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "docuColor", "Documentation color ",
			"",
			parent,
			true, true,
			false);
		fields.add(docuColor);

		ColorFieldEditor commColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "commColor", "Comment color       ",
			"",
			parent,
			true, true,
			false);
		fields.add(commColor);

		ColorFieldEditor conidColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "conidColor", "Constructor id color",
			"",
			parent,
			true, true,
			false);
		fields.add(conidColor);

		ColorFieldEditor varidColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "varidColor", "Variable id color   ",
			"",
			parent,
			true, true,
			false);
		fields.add(varidColor);

		ColorFieldEditor keywordColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "keywordColor", "Keyword color       ",
			"",
			parent,
			true, true,
			false);
		fields.add(keywordColor);

		ColorFieldEditor specialColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "specialColor", "Special symbol color",
			"",
			parent,
			true, true,
			false);
		fields.add(specialColor);

		ColorFieldEditor opColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "opColor", "Operator color      ",
			"",
			parent,
			true, true,
			false);
		fields.add(opColor);

		ColorFieldEditor literalColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "literalColor", "Literal color       ",
			"",
			parent,
			true, true,
			false);
		fields.add(literalColor);

		ColorFieldEditor errorColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "errorColor", "Error token color   ",
			"",
			parent,
			true, true,
			false);
		fields.add(errorColor);

		return fields.toArray(new FieldEditor[fields.size()]);
	}
}
