package com.mycompany.myapp.ui;

import com.codename1.ui.Container;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.FilterProxyListModel;
import java.util.Collection;

/**
 *
 * @author Curt
 * @param <T>
 */
public final class SearchableList<T> {

    private final TextField searchTerm = new TextField();
    private final DefaultListModel<T> underlyingListModel;
    private final FilterProxyListModel<T> filterProxyListModel;
    private final List<T> filteredList;

    public final Container component;
    
    public SearchableList(Collection<T> items) {
        component = new Container();
        underlyingListModel = new DefaultListModel(items);
        filterProxyListModel = new FilterProxyListModel(underlyingListModel);
        filteredList = new List(filterProxyListModel);
        FilterProxyListModel.install(searchTerm, filteredList);
        component.addComponent(searchTerm);
        component.addComponent(filteredList);
    }

    public void onSelected(ActionListener actionListener) {
        filteredList.addActionListener(actionListener);
    }

    public T getSelected() {
        return filterProxyListModel.getItemAt(filteredList.getSelectedIndex());
    }

}
