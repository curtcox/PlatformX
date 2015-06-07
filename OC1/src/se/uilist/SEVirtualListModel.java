package se.uilist;

import common.event.Change;
import common.event.LiveList;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * A ListModel that uses a given list to store its elements.
 */
public final class SEVirtualListModel<T>
    implements ListModel<T>
{
    private final List<T> items;

    private SEVirtualListModel(List items) {
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

    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }

}
