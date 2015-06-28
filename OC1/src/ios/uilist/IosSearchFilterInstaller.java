package ios.uilist;

import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

public final class IosSearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void anSpecificInstall(final IosSearchableList list, final StringToListFilter stringToListFilter) {
    }

    static void setFilterText(IosFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
        model.dataChanged();
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        anSpecificInstall((IosSearchableList) list, stringToListFilter);
    }
}
