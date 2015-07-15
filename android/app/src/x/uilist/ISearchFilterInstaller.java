package x.uilist;

import x.uiwidget.XSearchableList;

public interface ISearchFilterInstaller {
    void install(final XSearchableList list, final StringToListFilter stringToListFilter);
}
