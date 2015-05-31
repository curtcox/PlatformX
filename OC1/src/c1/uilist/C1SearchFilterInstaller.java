package c1.uilist;

import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import common.uilist.StringToListFilter;

public final class C1SearchFilterInstaller {
    
    public static void install(final C1SearchableList list, final StringToListFilter stringToListFilter) {
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

}
