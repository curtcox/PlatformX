package c1.uilist;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.list.ListModel;
import common.event.Change;
import common.uilist.IListModel;

public class C1ListModelAsIListModel
    implements IListModel
{
    private final ListModel model;

    public C1ListModelAsIListModel(C1FilterListModel model) {
        this.model = model;
    }

    @Override
    public int getSize() {
        return model.getSize();
    }

    @Override
    public void addItem(Object o) {
        model.addItem(o);
    }

    @Override
    public Object getItemAt(int i) {
        return model.getItemAt(i);
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
    public void addListener(final Change.Listener listener) {
        model.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int i, int i1) {
                listener.onChange();
            }
        });
    }
}
