package an.a22.uilist;

import common.uilist.ISearchFilterInstaller;
import common.uilist.StringToListFilter;
import common.uiwidget.ISearchableList;

public final class AnSearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void anSpecificInstall(final AnSearchableList list, final StringToListFilter stringToListFilter) {
    }

    static void setFilterText(AnFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
        model.dataChanged();
    }

    @Override
    public void install(ISearchableList list, StringToListFilter stringToListFilter) {
        anSpecificInstall((AnSearchableList) list, stringToListFilter);
    }
}
