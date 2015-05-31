package c1.uilist;

import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import common.event.SwappableList;
import common.uilist.StringToList;

public final class C1ListContentInstaller {
    
    public static void install(final C1SearchableList list, final SwappableList items, final StringToList stringToList) {
        final TextField search = list.searchTerm;
        search.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                items.become(stringToList.listFor(search.getText()));
            }
        });        
    }

}
