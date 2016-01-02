package x.uilist;

import x.uiwidget.XSearchableList;

public interface XSearchFilterInstaller {
    void install(final XSearchableList list, final StringToListFilter stringToListFilter);
}
