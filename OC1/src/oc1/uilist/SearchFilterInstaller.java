package oc1.uilist;

import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;

/**
 *
 * @author Curt
 */
public final class SearchFilterInstaller {
    
    public static void install(final SearchableList list, final StringToListFilter stringToListFilter) {
        final TextField search = list.searchTerm;
        search.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                setFilter(list.filterListModel);
            }
            
            void setFilter(FilterListModel model) {
                model.setFilter(stringToListFilter.listFilterFor(search.getText()));
                model.dataChanged(DataChangedListener.CHANGED, -1);
            }
        });        
    }

}
