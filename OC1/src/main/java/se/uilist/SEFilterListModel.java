package se.uilist;

import x.event.Change;
import x.event.LiveList;
import x.uilist.ListFilter;
import x.uilist.XListOffsets;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

final class SEFilterListModel<T>
    implements ListModel<T>
{
    private final LiveList<T> filtered;
    private final XListOffsets<T> offsets;
    private ListDataListener listDataListener;

    private SEFilterListModel(LiveList<T> filtered) {
        this.filtered = filtered;
        this.offsets = XListOffsets.of(filtered);
    }

    static SEFilterListModel of(LiveList filtered) {
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
        return offsets.getSize();
    }

    @Override
    public T getElementAt(int index) {
        return offsets.getElementAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {
        this.listDataListener = listDataListener;
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

    void setFilter(ListFilter filter) {
        offsets.setFilter(filter);
        dataChanged();
    }

    public void dataChanged() {
        offsets.calculate();
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
