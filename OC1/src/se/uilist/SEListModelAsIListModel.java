package se.uilist;

import x.event.Change;
import x.uilist.IListModel;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

final class SEListModelAsIListModel
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
    public Object getItemAt(int index) {
        return model.getElementAt(index);
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public void setSelectedIndex(int i) {
    }

    @Override
    public void addListener(final Change.Listener listener) {
        model.addListDataListener(new ListDataListener() {
            @Override public void intervalAdded(ListDataEvent listDataEvent) {
                listener.onChange();
            }
            @Override public void intervalRemoved(ListDataEvent listDataEvent) {
                listener.onChange();
            }
            @Override public void contentsChanged(ListDataEvent listDataEvent) {
                listener.onChange();
            }
        });
    }
}
