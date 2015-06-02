package c1.uilist;

import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import common.event.SwappableList;
import common.uilist.IListContentInstaller;
import common.uilist.StringToList;
import common.uiwidget.ISearchableList;

public final class C1ListContentInstaller
    implements IListContentInstaller {

    private static void c1SpecificInstall(final C1SearchableList list, final SwappableList items, final StringToList stringToList) {
        final TextField search = list.searchTerm;
        search.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                items.become(stringToList.listFor(search.getText()));
            }
        });
    }

    public void install(ISearchableList list, final SwappableList items, final StringToList stringToList) {
        c1SpecificInstall((C1SearchableList) list,items,stringToList);
    }
}