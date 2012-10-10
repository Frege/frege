/**
 * <p> Items in the tree must have the ability to compute their label, image and position. </p>
 */
package frege.imp.tree;

import org.eclipse.swt.graphics.Image;

import frege.compiler.BaseTypes.TPosition;

/**
 * @author ingo
 *
 */
public interface ITreeItem {
	public Image getImage();
	public String getLabel();
	public TPosition getPosition();
}
