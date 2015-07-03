package va.uilist;

import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

public final class VaSearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void anSpecificInstall(final VaSearchableList list, final StringToListFilter stringToListFilter) {
    }

    static void setFilterText(VaFilterListModel model, StringToListFilter stringToListFilter, String text) {
        model.setFilter(stringToListFilter.listFilterFor(text));
        model.dataChanged();
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        anSpecificInstall((VaSearchableList) list, stringToListFilter);
    }
}
