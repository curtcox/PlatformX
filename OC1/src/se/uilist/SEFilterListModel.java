package se.uilist;

import common.uilist.ListFilter;

import javax.swing.*;
import javax.swing.event.ListDataListener;

public final class SEFilterListModel<T>
    implements ListModel<T>
{
    public SEFilterListModel(ListModel<T> underlyingListModel) {

    }

    @Override
    public int getSize() {
        return 0;
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

    public void setFilter(ListFilter filter) {
    }

    public void dataChanged() {
    }
}
