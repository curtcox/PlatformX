package x.uilist;

import x.event.SwappableList;
import x.uiwidget.XSearchableList;

public interface XListContentInstaller {
    void install(XSearchableList list, final SwappableList items, final StringToList stringToList);
}
