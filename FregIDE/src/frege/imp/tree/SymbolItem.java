package frege.imp.tree;

import org.eclipse.swt.graphics.Image;

import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TQName;
import frege.compiler.Data.TVisibility;
import frege.compiler.BaseTypes.TPosition;
import frege.compiler.Data.TSymbol;
import frege.compiler.EclipseUtil;

public class SymbolItem implements ITreeItem {
	final TSymbol symbol;
	final TGlobal global;
	
	public SymbolItem(TGlobal g, TSymbol sy) { global = g; symbol = sy; }

	@Override
	public Image getImage() {
		final int c = symbol.constructor();
		if (c >= 0 && c < FregeLabelProvider.SYMBOL_IMAGES.length) {
			Image image = FregeLabelProvider.SYMBOL_IMAGES[c];
			if (image == FregeLabelProvider.VAR_IMAGE 
					&& (TSymbol.M.vis(symbol).j != TVisibility.Public.j
						|| TQName.M.isLocal(TSymbol.M.name(symbol))))
				image = FregeLabelProvider.LOCAL_IMAGE;
			return image;
		}
		return FregeLabelProvider.OUTLINE_IMAGE;
	}

	@Override
	public String getLabel() {
		return EclipseUtil.label(global, symbol);
	}

	@Override
	public TPosition getPosition() {
		return TSymbol.M.pos(symbol);
	}

}
