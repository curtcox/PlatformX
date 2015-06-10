package se.uilist;

import common.event.Change;
import common.event.LiveList;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * A ListModel that uses a given list to store its elements.
 */
public final class SEVirtualListModel<T>
    implements ListModel<T>
{
    private final LiveList<T> items;

    private SEVirtualListModel(LiveList items) {
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
    public T getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void addListDataListener(final ListDataListener listDataListener) {
        items.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                listDataListener.contentsChanged(dataChangedEvent());
            }
        });
    }

    private ListDataEvent dataChangedEvent() {
        return new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,items.size() - 1);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

}
