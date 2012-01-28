package frege.imp.tree;

import org.eclipse.swt.graphics.Image;

import frege.compiler.Data.TGlobal;
import frege.compiler.Data.TPosition;
import frege.compiler.Data.TSymbol;
import frege.compiler.EclipseUtil;

public class SymbolItem implements ITreeItem {
	final TSymbol symbol;
	final TGlobal global;
	
	public SymbolItem(TGlobal g, TSymbol sy) { global = g; symbol = sy; }

	@Override
	public Image getImage() {
		final int c = symbol.constructor();
		if (c >= 0 && c < FregeLabelProvider.SYMBOL_IMAGES.length)
			return  FregeLabelProvider.SYMBOL_IMAGES[c];
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
