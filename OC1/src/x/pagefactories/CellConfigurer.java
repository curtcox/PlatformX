package x.pagefactories;

import x.uilist.IListCell;
import x.uilist.ListCellConfigurer;

public final class CellConfigurer
        implements ListCellConfigurer
{
    public void configureButton(IListCell button, Object item) {
        button.setFirstRowText(item.toString());
    }
}
