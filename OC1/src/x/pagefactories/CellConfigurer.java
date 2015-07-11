package x.pagefactories;

import x.uilist.IXListCell;
import x.uilist.ListCellConfigurer;

public final class CellConfigurer
        implements ListCellConfigurer
{
    public void configureButton(IXListCell button, Object item) {
        button.setFirstRowText(item.toString());
    }
}
