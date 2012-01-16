/**
 * A String and a TPosition describing a package
 */
package frege.imp.tree;

import org.eclipse.swt.graphics.Image;

import frege.compiler.Data.TPosition;

/**
 * @author ingo
 *
 */
public class PackageItem implements ITreeItem {

	final public String name;
	final public TPosition pos;
	
	public PackageItem(String n, TPosition p) { name = n; pos = p; }
	
	@Override
	public Image getImage() {
		return FregeLabelProvider.PACKAGE_IMAGE;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public TPosition getPosition() {
		return pos;
	}
}
