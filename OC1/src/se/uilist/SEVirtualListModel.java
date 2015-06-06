package se.uilist;

import common.event.Change;
import common.event.LiveList;

import javax.swing.*;
import javax.swing.event.ListDataListener;

/**
 * A ListModel that uses a given list to store its elements.
 */
public final class SEVirtualListModel<T>
    implements ListModel<T>
{
    private int index;
    private final java.util.List<T> items;

    private SEVirtualListModel(java.util.List items) {
        this.items = items;
    }

    public static SEVirtualListModel of(LiveList items) {
        SEVirtualListModel model = new SEVirtualListModel(items);
        items.addListener(convert(model));
        return model;
    }

    private static Change.Listener convert(SEVirtualListModel model) {
        return null;
    }

    public T getItemAt(int index){
        return items.get(index);
    }
    
    public int getSize() {
        return items.size();
    }

    @Override
    public T getElementAt(int i) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {

    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

    public int getSelectedIndex() {
        return index;
    }

    public void setSelectedIndex(int index) {
        this.index = index;
    }

    public void addItem(T item)       { throw unsupported(); }
    public void removeItem(int index) { throw unsupported(); }

    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }

}
