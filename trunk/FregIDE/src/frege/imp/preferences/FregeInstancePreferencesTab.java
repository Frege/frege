/******************************************/
/* WARNING: GENERATED FILE - DO NOT EDIT! */
/******************************************/
package frege.imp.preferences;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
			"Editor font",
			parent,
			true, true,
			false);
		fields.add(sourceFont);

		// Link sourceFontDetailsLink = fPrefUtils.createDetailsLink(parent, sourceFont, sourceFont.getChangeControl().getParent(), "Details ...");

		// sourceFontDetailsLink.setEnabled(true);
		// fDetailsLinks.add(sourceFontDetailsLink);
		
		GridData data = new GridData();
        data.horizontalAlignment = SWT.END;
        data.widthHint = 128;


		IntegerFieldEditor tabWidth = fPrefUtils.makeNewIntegerField(
			page, this, fPrefService,
			"instance", "tabWidth", "Tabulator Width",
			"Tells how many spaces the tab key will insert.",
			parent,
			true, true,
			true, "4",
			false);
		fields.add(tabWidth);

		BooleanFieldEditor spacesForTabs = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "spacesForTabs", "Tab inserts spaces",
			"Is it strongly suggested to not have tabulator characters in Frege source code.",
			parent,
			true, true,
			true, true,
			false);
		fields.add(spacesForTabs);
		
		BooleanFieldEditor enableInline = fPrefUtils.makeNewBooleanField(
				page, this, fPrefService,
				"instance", "enableInline", "Enable Inline",
				"Passes the -inline option to the compiler. (experimental)",
				parent,
				true, true,
				true, false,
				false);
		fields.add(enableInline);

//
//		Link spacesForTabsDetailsLink = fPrefUtils.createDetailsLink(parent, spacesForTabs, spacesForTabs.getChangeControl().getParent(), "Details ...");
//
//		spacesForTabsDetailsLink.setEnabled(true);
//		fDetailsLinks.add(spacesForTabsDetailsLink);

//		FileFieldEditor fregeLib = fPrefUtils.makeNewFileField(
//				page, this, fPrefService,
//				"instance", "fregeLib", "Frege library",
//				"The location of the Frege Standard Library, the default is to use the one that comes with fregIDE.",
//				parent,
//				true, true,
//				false, "",
//				false);
//			fields.add(fregeLib);
//
//			Link fregeLibDetailsLink = fPrefUtils.createDetailsLink(parent, fregeLib, fregeLib.getTextControl().getParent(), "Details ...");
//
//			fregeLibDetailsLink.setEnabled(false);
//			fDetailsLinks.add(fregeLibDetailsLink);
		
		ColorFieldEditor docuColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "docuColor", "Documentation",
			"Color for documentation comments",
			parent,
			true, true,
			false);
		docuColor.getLabelControl(docuColor.getParent()).setLayoutData(data);
		fields.add(docuColor);

		ColorFieldEditor commColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "commColor", "Comments        ",
			"Color for ordinary comments",
			parent,
			true, true,
			false);
		commColor.getLabelControl(commColor.getParent()).setLayoutData(data);
		fields.add(commColor);

		ColorFieldEditor tconColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "tconColor", "Types etc.",
			"Color for type constrcutors, type aliases, classes and namespaces",
			parent,
			true, true,
			false);
		tconColor.getLabelControl(tconColor.getParent()).setLayoutData(data);
		fields.add(tconColor);

		ColorFieldEditor dconColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "dconColor", "Constructors    ",
			"Color for data constrcutor names",
			parent,
			true, true,
			false);
		dconColor.getLabelControl(dconColor.getParent()).setLayoutData(data);
		fields.add(dconColor);


		ColorFieldEditor varidColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "varidColor", "Top level vars",
			"Color for non-local variables defined in the current package",
			parent,
			true, true,
			false);
		varidColor.getLabelControl(varidColor.getParent()).setLayoutData(data);
		fields.add(varidColor);
		
		ColorFieldEditor importColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "importColor", "Imported vars",
			"Color for variables and functions defined in an imported package",
			parent,
			true, true,
			false);
		importColor.getLabelControl(importColor.getParent()).setLayoutData(data);
		fields.add(importColor);
		
		ColorFieldEditor keywordColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "keywordColor", "Reserved words",
			"Color for key words.",
			parent,
			true, true,
			false);
		keywordColor.getLabelControl(keywordColor.getParent()).setLayoutData(data);
		fields.add(keywordColor);

		ColorFieldEditor specialColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "specialColor", "Special symbols ",
			"Color for symbols ::, ->, <-, => and |",
			parent,
			true, true,
			false);
		specialColor.getLabelControl(specialColor.getParent()).setLayoutData(data);
		fields.add(specialColor);

		ColorFieldEditor literalColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "literalColor", "Literal values",
			"Colors for literal numbers, strings, characters and regular expressions",
			parent,
			true, true,
			false);
		literalColor.getLabelControl(literalColor.getParent()).setLayoutData(data);
		fields.add(literalColor);

		ColorFieldEditor errorColor = fPrefUtils.makeNewColorField(
			page, this, fPrefService,
			"instance", "errorColor", "Lexical errors",
			"Color that signals unfinished block comments and quoted constructs",
			parent,
			true, true,
			false);
		
        errorColor.getLabelControl(errorColor.getParent()).setLayoutData(data);
		fields.add(errorColor);

		BooleanFieldEditor italicImports = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "italicImports", "Show imported items in italics",
			"Gives a visual clue about whether a name was imported.",
			parent,
			true, true,
			true, false,
			false);
		fields.add(italicImports);
		
		BooleanFieldEditor boldNS = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "boldNS", "Show name spaces in bold face",
			"Gives a visual clue about whether a qualifier is not a type, but a namespace.",
			parent,
			true, true,
			true, false,
			false);
		fields.add(boldNS);
		
		BooleanFieldEditor decoS = fPrefUtils.makeNewBooleanField(
				page, this, fPrefService,
				"instance", "decorateStrict", "Decorate strict local names",
				"Gives a visual clue about whether a local name (such as a function argument) is strict. (not yet implemented)",
				parent,
				true, true,
				true, false,
				false);
		fields.add(decoS);
		
		IntegerFieldEditor parseTimeout = fPrefUtils.makeNewIntegerField(
		page, this, fPrefService,
		"instance", "parseTimeout", "Parser Timeout",
		"Time in ms before the parser starts after a keystroke.",
		parent,
		true, true,
		true, "250",
		false);
		fields.add(parseTimeout);

		
		return fields.toArray(new FieldEditor[fields.size()]);
	}
}
