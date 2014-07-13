package oc1.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.ListModel;
import oc1.event.LiveList;

/**
 * A wrapper for a searchable list component.
 */
public final class SearchableList<T> {

    private final TextField searchTerm = new TextField();
    private final ListModel<T> underlyingListModel;
    private final FilterListModel<T> filterListModel;
    private final IList filteredList;

    public final BorderContainer component;

    private SearchableList(IList.Factory factory, LiveList<T> items, Component action, ListCellConfigurer configurer, StringToListFilter stringToListFilter) {
        underlyingListModel = VirtualListModel.of(items);
        filterListModel = new FilterListModel(underlyingListModel);
        filteredList = factory.of(filterListModel,configurer);
        SearchFieldInstaller.install(searchTerm, filterListModel, stringToListFilter);
        component = new BorderContainer((Component)filteredList)
             .addNorth(newNorthContainer(action));
    }

    public SearchableList(LiveList<T> items, Component action, ListCellConfigurer configurer, StringToListFilter stringToListFilter) {
        this(IList.BOX,items,action,configurer,stringToListFilter);
    }

    private Container newNorthContainer(Component action) {
        return new BorderContainer(searchTerm).addEast(action);
    }
    
    public void onSelected(ActionListener actionListener) {
        filteredList.addActionListener(actionListener);
    }

    public T getSelected() {
        return filterListModel.getItemAt(filteredList.getSelectedIndex());
    }

}
