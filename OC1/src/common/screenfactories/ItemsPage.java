package common.screenfactories;

import common.screen.ScreenLink;
import common.screen.SelectionListPage;
import common.uiwidget.ISearchableList;

public final class ItemsPage
    extends SelectionListPage
{
    public ItemsPage(ScreenLink link, ISearchableList values) {
        super(link,values);
    }

    @Override
    protected ScreenLink useSelectedItem(Object item) {
        return ScreenLink.of(item.toString());
    }
}
