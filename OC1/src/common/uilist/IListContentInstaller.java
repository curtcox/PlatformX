package common.uilist;

import common.event.SwappableList;
import common.uiwidget.ISearchableList;

public interface IListContentInstaller {
    void install(ISearchableList list, final SwappableList items, final StringToList stringToList);
}
