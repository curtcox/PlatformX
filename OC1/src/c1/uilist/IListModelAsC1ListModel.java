package c1.uilist;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import common.uilist.IListModel;

public final class IListModelAsC1ListModel
   implements ListModel
{
    public IListModelAsC1ListModel(IListModel model) {

    }

    @Override
    public Object getItemAt(int i) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public void setSelectedIndex(int i) {

    }

    @Override
    public void addDataChangedListener(DataChangedListener dataChangedListener) {

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

    }

    @Override
    public void removeItem(int i) {

    }
}
