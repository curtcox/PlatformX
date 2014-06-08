package oc1.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.FilterProxyListModel;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.list.ListModel;
import oc1.event.LiveList;

/**
 * A wrapper for a searchable list component.
 */
public final class SearchableList<T> {

    private final TextField searchTerm = new TextField();
    private final ListModel<T> underlyingListModel;
    private final FilterProxyListModel<T> filterProxyListModel;
    private final List filteredList;

    public final BorderContainer component;
    
    public SearchableList(LiveList<T> items, Component action, ListCellRenderer renderer) {
        underlyingListModel = VirtualListModel.of(items);
        filterProxyListModel = new FilterProxyListModel(underlyingListModel);
        filteredList = newList(filterProxyListModel,renderer);
        FilterProxyListModel.install(searchTerm, filteredList);
        component = new BorderContainer(filteredList)
             .addNorth(newNorthContainer(action));
    }
    
    private static List newList(ListModel model,ListCellRenderer renderer) {
        List list = new List(model);       
        list.setRenderer(renderer);
        return list;
    }

    private Container newNorthContainer(Component action) {
        return new BorderContainer(searchTerm).addEast(action);
    }
    
    public void onSelected(ActionListener actionListener) {
        filteredList.addActionListener(actionListener);
    }

    public T getSelected() {
        return filterProxyListModel.getItemAt(filteredList.getSelectedIndex());
    }

}
