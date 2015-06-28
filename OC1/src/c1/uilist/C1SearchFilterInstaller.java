package c1.uilist;

import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import x.uilist.ISearchFilterInstaller;
import x.uilist.StringToListFilter;
import x.uiwidget.XSearchableList;

public final class C1SearchFilterInstaller
    implements ISearchFilterInstaller
{
    
    public static void c1SpecificInstall(final C1SearchableList list, final StringToListFilter stringToListFilter) {
        final TextField search = list.searchTerm;
        search.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                setFilter(list.filterListModel);
            }
            
            void setFilter(C1FilterListModel model) {
                model.setFilter(stringToListFilter.listFilterFor(search.getText()));
                model.dataChanged(DataChangedListener.CHANGED, -1);
            }
        });        
    }

    @Override
    public void install(XSearchableList list, StringToListFilter stringToListFilter) {
        c1SpecificInstall((C1SearchableList) list,stringToListFilter);
    }
}
