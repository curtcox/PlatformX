package common.pagefactories;

import common.uilist.IListCell;
import common.uilist.ListCellConfigurer;

public final class CellConfigurer
        implements ListCellConfigurer
{
    public void configureButton(IListCell button, Object item) {
        button.setFirstRowText(item.toString());
    }
}
