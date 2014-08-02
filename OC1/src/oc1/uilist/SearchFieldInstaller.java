package oc1.uilist;

import oc1.uilist.StringToListFilter;
import oc1.uilist.FilterListModel;
import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;

/**
 *
 * @author Curt
 */
final class SearchFieldInstaller {
    
    static void install(final TextField search, final FilterListModel list, final StringToListFilter stringToListFilter) {
        search.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                list.setFilter(stringToListFilter.listFilterFor(search.getText()));
                list.dataChanged(DataChangedListener.CHANGED, -1);
            }
        });        
    }

}
