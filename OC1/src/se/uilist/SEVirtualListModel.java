package se.uilist;

import x.event.Change;
import x.event.LiveList;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * A ListModel that uses a given list to store its elements.
 */
final class SEVirtualListModel<T>
    implements ListModel<T>
{
    private final LiveList<T> items;
    private ListDataListener listDataListener;

    private SEVirtualListModel(LiveList items) {
        this.items = items;
    }

    public static SEVirtualListModel of(LiveList items) {
        SEVirtualListModel model = new SEVirtualListModel(items);
        model.listenForItemChanges();
        return model;
    }

    private void listenForItemChanges() {
        items.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                listDataListener.contentsChanged(dataChangedEvent());
            }
        });
    }

    public int getSize() {
        return items.size();
    }

    @Override
    public T getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void addListDataListener(final ListDataListener listDataListener) {
        this.listDataListener = listDataListener;
    }

    private ListDataEvent dataChangedEvent() {
        return new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,items.size() - 1);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

}
