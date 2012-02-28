package frege.imp.tree;

import org.eclipse.swt.graphics.Image;

import frege.compiler.BaseTypes.TPosition;

public class CategoryItem implements ITreeItem {
	final String label;
	final TPosition pos;
	
	public CategoryItem(String n, TPosition p) {
		label = n; pos = p; 
	}

	@Override
	public Image getImage() {
		return FregeLabelProvider.OUTLINE_IMAGE;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public TPosition getPosition() {
		return pos;
	}

}
