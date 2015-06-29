package se.uilist;

import x.event.Change;
import x.event.LiveList;
import x.uilist.ListFilter;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

final class SEFilterListModel<T>
    implements ListModel<T>
{
    private final LiveList<T> filtered;
    private ListFilter filter = ListFilter.ALLOW_ALL;
    private ListDataListener listDataListener;

    private SEFilterListModel(LiveList<T> filtered) {
        this.filtered = filtered;
        listenForModelChanges();
    }

    public static SEFilterListModel of(LiveList filtered) {
        SEFilterListModel model = new SEFilterListModel(filtered);
        model.listenForModelChanges();
        return model;
    }

    private void listenForModelChanges() {
        filtered.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataChanged();
            }
        });
    }

    @Override
    public int getSize() {
        int passed = 0;
        for (int i=0; i<filtered.size(); i++) {
            if (filter.passes(filtered.get(i))) {
                passed++;
            }
        }
        return passed;
    }

    @Override
    public T getElementAt(int index) {
        int passed = -1;
        for (int i=0; i<filtered.size(); i++) {
            T item = filtered.get(i);
            if (filter.passes(item)) {
                passed++;
            }
            if (passed==index) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {
        this.listDataListener = listDataListener;
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
        dataChanged();
    }

    public void dataChanged() {
        notifyDataListener();
    }

    private void notifyDataListener() {
        Object source = this;
        int type = ListDataEvent.CONTENTS_CHANGED;
        int first = 0;
        int last = getSize() - 1;
        listDataListener.contentsChanged(new ListDataEvent(source,type,first,last));
    }

}
