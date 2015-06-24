package x.uilist;

import x.event.SwappableList;
import x.uiwidget.ISearchableList;

public interface IListContentInstaller {
    void install(ISearchableList list, final SwappableList items, final StringToList stringToList);
}
