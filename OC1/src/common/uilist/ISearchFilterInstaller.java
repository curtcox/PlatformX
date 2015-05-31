package common.uilist;

import common.uiwidget.ISearchableList;

public interface ISearchFilterInstaller {
    void install(final ISearchableList list, final StringToListFilter stringToListFilter);
}
