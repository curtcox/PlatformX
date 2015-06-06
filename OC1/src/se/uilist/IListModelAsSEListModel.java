package se.uilist;

import common.uilist.IListModel;

import javax.swing.*;
import javax.swing.event.ListDataListener;

public final class IListModelAsSEListModel
   implements ListModel
{
    IListModel model;

    public IListModelAsSEListModel(IListModel model) {
        this.model = model;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object getElementAt(int i) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {

    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }
}
