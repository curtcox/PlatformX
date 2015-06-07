package se.uilist;

import common.uilist.ListFilter;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public final class SEFilterListModel<T>
    implements ListModel<T>
{
    private final ListModel<T> filtered;
    private ListFilter filter = ListFilter.ALLOW_ALL;
    private ListDataListener listDataListener;

    public SEFilterListModel(ListModel<T> filtered) {
        this.filtered = filtered;
    }

    @Override
    public int getSize() {
        int passed = 0;
        for (int i=0; i<filtered.getSize(); i++) {
            if (filter.passes(filtered.getElementAt(i))) {
                passed++;
            }
        }
        return passed;
    }

    @Override
    public T getElementAt(int index) {
        int passed = -1;
        for (int i=0; i<filtered.getSize(); i++) {
            T item = filtered.getElementAt(i);
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
        if (listDataListener!=null) {
            notifyDataListener();
        }
    }

    private void notifyDataListener() {
        Object source = this;
        int type = ListDataEvent.CONTENTS_CHANGED;
        int first = 0;
        int last = getSize() - 1;
        listDataListener.contentsChanged(new ListDataEvent(source,type,first,last));
    }

}
