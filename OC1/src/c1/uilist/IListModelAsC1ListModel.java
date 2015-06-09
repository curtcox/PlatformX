package c1.uilist;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import common.event.Change;
import common.uilist.IListModel;

public final class IListModelAsC1ListModel
   implements ListModel
{
    IListModel model;

    public IListModelAsC1ListModel(IListModel model) {
        this.model = model;
    }

    @Override
    public Object getItemAt(int i) {
        return model.getItemAt(i);
    }

    @Override
    public int getSize() {
        return model.getSize();
    }

    @Override
    public int getSelectedIndex() {
        return model.getSelectedIndex();
    }

    @Override
    public void setSelectedIndex(int i) {
        model.setSelectedIndex(i);
    }

    @Override
    public void addDataChangedListener(final DataChangedListener dataChangedListener) {
        model.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataChangedListener.dataChanged(DataChangedListener.CHANGED,0);
            }
        });
    }

    @Override
    public void removeDataChangedListener(DataChangedListener dataChangedListener) {

    }

    @Override
    public void addSelectionListener(SelectionListener selectionListener) {

    }

    @Override
    public void removeSelectionListener(SelectionListener selectionListener) {

    }

    @Override
    public void addItem(Object o) {
        model.addItem(o);
    }

    @Override
    public void removeItem(int i) {

    }
}
