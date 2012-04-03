package frege.imp.editorActions;

import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.services.ILanguageActionsContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
// import org.eclipse.ui.IFileEditorInput;

public class FregeEditorActionContributions implements
		ILanguageActionsContributor {
	
	
	private Action fgAction(final UniversalEditor uditor) {
		Action it = new Action("f • g") {
			public void run() {
				UniversalEditor editor = uditor;
				if (editor == null) {
					IEditorPart ed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					if (ed == null) return;
					if (ed instanceof UniversalEditor)
						editor = (UniversalEditor) ed;
					else return;
				}
				// editor is not null
				try {
					if (!editor.isEditable()) return;
					final Point where = editor.getSelection();
					// System.err.println("we are at " + where);
			        final IDocument document= editor.
			        		getDocumentProvider().
			        		getDocument(editor.getEditorInput());
			        if (document != null) {
			        	document.replace(where.x, where.y, "•");
			        	editor.selectAndReveal(where.x+1, 0);
			        }
				} 
			    catch (BadLocationException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		};
		it.setDescription("Insert the • symbol for the function composition operator.");
		it.setToolTipText(it.getDescription());
		it.setId("frege-editoraction-bullet");
		return it;
	}
	
	private Action rgxAction(final UniversalEditor uditor) {
		Action it = new Action("´regex´") {
			public void run() {
				UniversalEditor editor = uditor;
				if (editor == null) {
					IEditorPart ed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					if (ed == null) return;
					// System.err.println(ed.getClass().getName());
					if (ed instanceof UniversalEditor)
						editor = (UniversalEditor) ed;
					else return;
				}
				// editor is not null
				try {
					if (!editor.isEditable()) return;
					final Point where = editor.getSelection();
					// System.err.println("we are at " + where);
			        final IDocument document= editor.
			        		getDocumentProvider().
			        		getDocument(editor.getEditorInput());
			        if (document != null) {
			        	String w = document.get(where.x, where.y);
			        	document.replace(where.x, where.y, "´" + w + "´");
			        	if (w.length() == 0)
			        		editor.selectAndReveal(where.x+1, 0);
			        }
				} 
			    catch (BadLocationException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		};
		it.setDescription("Enclose the selected text in grave accent marks.");
		it.setToolTipText(it.getDescription());
		it.setId("frege-editoraction-rgx");
		return it;
	}

	public void contributeToEditorMenu(final UniversalEditor editor,
			IMenuManager menuManager) {
		IMenuManager languageMenu = new MenuManager("Frege");
		menuManager.add(languageMenu);
		languageMenu.add(fgAction(editor));
		languageMenu.add(rgxAction(editor));
	}

	public void contributeToMenuBar(UniversalEditor editor, IMenuManager menu) {
		// TODO implement contributions and add them to the menu
		
	}

	public void contributeToStatusLine(final UniversalEditor editor,
			IStatusLineManager statusLineManager) {
		// TODO add ControlContribution objects to the statusLineManager
	}

	public void contributeToToolBar(UniversalEditor editor,
			IToolBarManager toolbarManager) {
		
		Action fg = fgAction(null);
		if (toolbarManager.find(fg.getId()) == null)
			toolbarManager.add(fg);
		Action rgx = rgxAction(null);
		if (toolbarManager.find(rgx.getId()) == null)
			toolbarManager.add(rgx);
	}
}
