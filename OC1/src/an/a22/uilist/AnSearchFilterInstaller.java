package an.a22.uilist;

import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.ISearchableList;

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
