package common.screenfactories;

import common.screen.ScreenLink;
import common.screen.SelectionListScreen;
import common.uiwidget.ISearchableList;

public final class ItemsScreen
    extends SelectionListScreen
{
    public ItemsScreen(ScreenLink link, ISearchableList values) {
        super(link,values);
    }

    @Override
    protected ScreenLink useSelectedItem(Object item) {
        return ScreenLink.of(item.toString());
    }
}
