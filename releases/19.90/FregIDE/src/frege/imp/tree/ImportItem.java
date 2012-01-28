package frege.imp.tree;

import org.eclipse.swt.graphics.Image;

import frege.compiler.Data.TPosition;

public class ImportItem implements ITreeItem {
	final TPosition pos;
	final String    ns, pack;
	
	public ImportItem(TPosition pos, String ns, String pack) {
		this.pos = pos;
		this.ns  = ns;
		this.pack = pack;
	}
	
	@Override
	public Image getImage() {
		return FregeLabelProvider.IMPORT_IMAGE;
	}

	@Override
	public String getLabel() {
		return ns + ": " + pack;
	}

	@Override
	public TPosition getPosition() {
		return pos;
	}

}
