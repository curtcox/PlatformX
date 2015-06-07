package se.uilist;

import common.uilist.ListFilter;

import javax.swing.*;
import javax.swing.event.ListDataListener;

public final class SEFilterListModel<T>
    implements ListModel<T>
{
    private final ListModel<T> filtered;

    public SEFilterListModel(ListModel<T> filtered) {
        this.filtered = filtered;
    }

    @Override
    public int getSize() {
        return filtered.getSize();
    }

    @Override
    public T getElementAt(int index) {
        return filtered.getElementAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {

    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

    public void setFilter(ListFilter filter) {
    }

    public void dataChanged() {
    }
}
