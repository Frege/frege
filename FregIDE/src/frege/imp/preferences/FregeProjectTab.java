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
 * The project level preferences tab.
 */
public class FregeProjectTab extends ProjectPreferencesTab {

	public FregeProjectTab(IPreferencesService prefService) {
		super(prefService, false);
	}

	/**
	 * Creates specific preference fields with settings appropriate to
	 * the project preferences level.
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
			"project", "sourceFont", "source font",
			"",
			parent,
			false, false,
			false);
		fields.add(sourceFont);

		Link sourceFontDetailsLink = fPrefUtils.createDetailsLink(parent, sourceFont, sourceFont.getChangeControl().getParent(), "Details ...");

		sourceFontDetailsLink.setEnabled(false);
		fDetailsLinks.add(sourceFontDetailsLink);


		BooleanFieldEditor warningsOn = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"project", "warningsOn", "warnings on",
			"",
			parent,
			false, false,
			true, false,
			false);
		fields.add(warningsOn);

		Link warningsOnDetailsLink = fPrefUtils.createDetailsLink(parent, warningsOn, warningsOn.getChangeControl().getParent(), "Details ...");

		warningsOnDetailsLink.setEnabled(false);
		fDetailsLinks.add(warningsOnDetailsLink);


		DirectoryListFieldEditor FregePath = fPrefUtils.makeNewDirectoryListField(
			page, this, fPrefService,
			"project", "FregePath", "Frege path",
			"A semicolon-separated list of folders to search for import files",
			parent,
			false, false,
			false, "",
			false);
		fields.add(FregePath);

		Link FregePathDetailsLink = fPrefUtils.createDetailsLink(parent, FregePath, FregePath.getTextControl().getParent(), "Details ...");

		FregePathDetailsLink.setEnabled(false);
		fDetailsLinks.add(FregePathDetailsLink);


		DirectoryFieldEditor Destination = fPrefUtils.makeNewDirectoryField(
			page, this, fPrefService,
			"project", "Destination", "Destination",
			"Where to put compiled java and class files.",
			parent,
			false, false,
			false, "",
			false);
		fields.add(Destination);

		Link DestinationDetailsLink = fPrefUtils.createDetailsLink(parent, Destination, Destination.getTextControl().getParent(), "Details ...");

		DestinationDetailsLink.setEnabled(false);
		fDetailsLinks.add(DestinationDetailsLink);

		return fields.toArray(new FieldEditor[fields.size()]);
	}



	protected void addressProjectSelection(IPreferencesService.ProjectSelectionEvent event, Composite composite)
	{
		boolean haveCurrentListeners = false;

		Preferences oldNode = event.getPrevious();
		Preferences newNode = event.getNew();

		if (oldNode == null && newNode == null) {
			// Happens sometimes when you clear the project selection.
			// Nothing, really, to do in this case ...
			return;
		}

		// If oldNode is not null, we want to remove any preference-change listeners from it
		if (oldNode != null && oldNode instanceof IEclipsePreferences && haveCurrentListeners) {
			removeProjectPreferenceChangeListeners();
			haveCurrentListeners = false;
		} else {
			// Print an advisory message if you want to
		}

		// Declare local references to the fields
		FontFieldEditor sourceFont = (FontFieldEditor) fFields[0];
		Link sourceFontDetailsLink = (Link) fDetailsLinks.get(0);
		BooleanFieldEditor warningsOn = (BooleanFieldEditor) fFields[1];
		Link warningsOnDetailsLink = (Link) fDetailsLinks.get(1);
		DirectoryListFieldEditor FregePath = (DirectoryListFieldEditor) fFields[2];
		Link FregePathDetailsLink = (Link) fDetailsLinks.get(2);
		DirectoryFieldEditor Destination = (DirectoryFieldEditor) fFields[3];
		Link DestinationDetailsLink = (Link) fDetailsLinks.get(3);

		// If we have a new project preferences node, then do various things
		// to set up the project's preferences
		if (newNode != null && newNode instanceof IEclipsePreferences) {
			// If the containing composite is not disposed, then set field values
			// and make them enabled and editable (as appropriate to the type of field)

			if (!composite.isDisposed()) {
				// Note:  Where there are toggles between fields, it is a good idea to set the
				// properties of the dependent field here according to the values they should have
				// based on the independent field.  There should be listeners to take care of 
				// that sort of adjustment once the tab is established, but when properties are
				// first initialized here, the properties may not always be set correctly through
				// the toggle.  I'm not entirely sure why that happens, except that there may be
				// a race condition between the setting of the dependent values by the listener
				// and the setting of those values here.  If the values are set by the listener
				// first (which might be surprising, but may be possible) then they will be
				// overwritten by values set here--so the values set here should be consistent
				// with what the listener would set.

				fPrefUtils.setField(sourceFont, sourceFont.getHolder());
				sourceFont.getChangeControl().setEnabled(true);
				sourceFontDetailsLink.setEnabled(selectedProjectCombo.getText().length() > 0);

				fPrefUtils.setField(warningsOn, warningsOn.getHolder());
				warningsOn.getChangeControl().setEnabled(true);
				warningsOnDetailsLink.setEnabled(selectedProjectCombo.getText().length() > 0);

				fPrefUtils.setField(FregePath, FregePath.getHolder());
				FregePath.getTextControl().setEditable(true);
				FregePath.getTextControl().setEnabled(true);
				FregePath.setEnabled(true, FregePath.getParent());
				FregePathDetailsLink.setEnabled(selectedProjectCombo.getText().length() > 0);

				fPrefUtils.setField(Destination, Destination.getHolder());
				Destination.getTextControl().setEditable(true);
				Destination.getTextControl().setEnabled(true);
				Destination.setEnabled(true, Destination.getParent());
				DestinationDetailsLink.setEnabled(selectedProjectCombo.getText().length() > 0);

				clearModifiedMarksOnLabels();
			}

			// Add property change listeners
			if (sourceFont.getHolder() != null) addProjectPreferenceChangeListeners(sourceFont, "sourceFont", sourceFont.getHolder());
			if (warningsOn.getHolder() != null) addProjectPreferenceChangeListeners(warningsOn, "warningsOn", warningsOn.getHolder());
			if (FregePath.getHolder() != null) addProjectPreferenceChangeListeners(FregePath, "FregePath", FregePath.getHolder());
			if (Destination.getHolder() != null) addProjectPreferenceChangeListeners(Destination, "Destination", Destination.getHolder());

			haveCurrentListeners = true;
		}

		// Or if we don't have a new project preferences node ...
		if (newNode == null || !(newNode instanceof IEclipsePreferences)) {
			// May happen when the preferences page is first brought up, or
			// if we allow the project to be deselected\nn			// Clear the preferences from the store
			fPrefService.clearPreferencesAtLevel(IPreferencesService.PROJECT_LEVEL);

			// Disable fields and make them non-editable
			if (!composite.isDisposed()) {
				sourceFont.getChangeControl().setEnabled(false);
				warningsOn.getChangeControl().setEnabled(false);
				FregePath.getTextControl().setEditable(false);
				FregePath.getTextControl().setEnabled(false);
				FregePath.setEnabled(false, FregePath.getParent());
				Destination.getTextControl().setEditable(false);
				Destination.getTextControl().setEnabled(false);
				Destination.setEnabled(false, Destination.getParent());
			}

			// Remove listeners
			removeProjectPreferenceChangeListeners();
			haveCurrentListeners = false;
			// To help assure that field properties are established properly
			performApply();
		}
	}


}
