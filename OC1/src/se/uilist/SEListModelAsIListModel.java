package se.uilist;

import common.uilist.IListModel;

import javax.swing.*;

public class SEListModelAsIListModel
    implements IListModel
{
    private final ListModel model;

    public SEListModelAsIListModel(SEFilterListModel model) {
        this.model = model;
    }

    @Override
    public int getSize() {
        return model.getSize();
    }

    @Override
    public void addItem(Object o) {
    }

    @Override
    public Object getItemAt(int i) {
        return null;
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public void setSelectedIndex(int i) {
    }
}
