package x.uilist;

import x.uiwidget.ISearchableList;

public interface ISearchFilterInstaller {
    void install(final ISearchableList list, final StringToListFilter stringToListFilter);
}
