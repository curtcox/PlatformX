package com.mycompany.myapp.ui;

import com.codename1.ui.Container;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.FilterProxyListModel;
import com.codename1.ui.list.ListModel;
import com.mycompany.myapp.event.LiveList;

/**
 * A wrapper for a searchable list component.
 */
public final class SearchableList<T> {

    private final TextField searchTerm = new TextField();
    private final ListModel<T> underlyingListModel;
    private final FilterProxyListModel<T> filterProxyListModel;
    private final List<T> filteredList;

    public final BorderContainer component;
    
    public SearchableList(LiveList<T> items, ActionButton action) {
        underlyingListModel = VirtualListModel.of(items);
        filterProxyListModel = new FilterProxyListModel(underlyingListModel);
        filteredList = new List(filterProxyListModel);
        FilterProxyListModel.install(searchTerm, filteredList);
        component = new BorderContainer(filteredList)
             .addNorth(newNorthContainer(action));
    }

    private Container newNorthContainer(ActionButton action) {
        return new BorderContainer(searchTerm).addEast(action);
    }
    
    public void onSelected(ActionListener actionListener) {
        filteredList.addActionListener(actionListener);
    }

    public T getSelected() {
        return filterProxyListModel.getItemAt(filteredList.getSelectedIndex());
    }

}
