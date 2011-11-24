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
public class FregeInstanceTab extends InstancePreferencesTab {

	public FregeInstanceTab(IPreferencesService prefService) {
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
			"instance", "sourceFont", "source font",
			"",
			parent,
			true, true,
			false);
		fields.add(sourceFont);

		Link sourceFontDetailsLink = fPrefUtils.createDetailsLink(parent, sourceFont, sourceFont.getChangeControl().getParent(), "Details ...");

		sourceFontDetailsLink.setEnabled(true);
		fDetailsLinks.add(sourceFontDetailsLink);


		BooleanFieldEditor warningsOn = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "warningsOn", "warnings on",
			"",
			parent,
			true, true,
			true, false,
			false);
		fields.add(warningsOn);

		Link warningsOnDetailsLink = fPrefUtils.createDetailsLink(parent, warningsOn, warningsOn.getChangeControl().getParent(), "Details ...");

		warningsOnDetailsLink.setEnabled(true);
		fDetailsLinks.add(warningsOnDetailsLink);


		BooleanFieldEditor UseDefaultImportPath = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "UseDefaultImportPath", "Use default import path",
			"",
			parent,
			true, true,
			true, false,
			false);
		fields.add(UseDefaultImportPath);

		Link UseDefaultImportPathDetailsLink = fPrefUtils.createDetailsLink(parent, UseDefaultImportPath, UseDefaultImportPath.getChangeControl().getParent(), "Details ...");

		UseDefaultImportPathDetailsLink.setEnabled(true);
		fDetailsLinks.add(UseDefaultImportPathDetailsLink);


		DirectoryListFieldEditor ImportPathToUse = fPrefUtils.makeNewDirectoryListField(
			page, this, fPrefService,
			"instance", "ImportPathToUse", "Import path to use",
			"A semicolon-separated list of folders to search for import files",
			parent,
			true, true,
			false, "",
			false);
		fields.add(ImportPathToUse);

		Link ImportPathToUseDetailsLink = fPrefUtils.createDetailsLink(parent, ImportPathToUse, ImportPathToUse.getTextControl().getParent(), "Details ...");

		ImportPathToUseDetailsLink.setEnabled(true);
		fDetailsLinks.add(ImportPathToUseDetailsLink);


		fPrefUtils.createToggleFieldListener(UseDefaultImportPath, ImportPathToUse, false);
		boolean isEnabledImportPathToUse = !UseDefaultImportPath.getBooleanValue();
				ImportPathToUse.getTextControl().setEditable(isEnabledImportPathToUse);
				ImportPathToUse.getTextControl().setEnabled(isEnabledImportPathToUse);
				ImportPathToUse.setEnabled(isEnabledImportPathToUse, ImportPathToUse.getParent());
		return fields.toArray(new FieldEditor[fields.size()]);
	}
}
