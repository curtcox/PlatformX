package an.a22.uilist;

import android.database.DataSetObserver;
import android.widget.ListAdapter;
import x.event.Change;
import x.uilist.IListModel;

public class AnListModelAsIListModel
    implements IListModel
{
    private final ListAdapter model;

    public AnListModelAsIListModel(AnFilterListModel model) {
        this.model = model;
    }

    @Override
    public int getSize() {
        return model.getCount();
    }

    @Override
    public void addItem(Object o) {
    }

    @Override
    public Object getItemAt(int index) {
        return model.getItem(index);
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
        model.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                listener.onChange();
            }
        });
    }
}
