package an.a22.uilist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import x.event.Change;
import x.uilist.IListModel;

public final class IListModelAsAnListModel
   implements ListAdapter
{
    private final IListModel model;

    public IListModelAsAnListModel(IListModel model) {
        this.model = model;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
        model.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataSetObserver.onChanged();
            }
        });
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return model.getSize();
    }

    @Override
    public Object getItem(int index) {
        return model.getItemAt(index);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return model.getSize()==0;
    }
}
