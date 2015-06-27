package se.uilist;

import x.event.Change;
import x.uilist.IListModel;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

final class IListModelAsSEListModel
   implements ListModel
{
    IListModel model;

    public IListModelAsSEListModel(IListModel model) {
        this.model = model;
    }

    @Override
    public int getSize() {
        return model.getSize();
    }

    @Override
    public Object getElementAt(int i) {
        return model.getItemAt(i);
    }

    @Override
    public void addListDataListener(final ListDataListener listDataListener) {
        this.model.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                listDataListener.contentsChanged(everythingChanged());
            }
        });
    }

    private ListDataEvent everythingChanged() {
        return new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,getSize() - 1);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }
}
