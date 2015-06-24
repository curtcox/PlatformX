package an.a22.uilist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import x.event.LiveList;

/**
 * A ListModel that uses a given list to store its elements.
 */
public final class AnVirtualListModel<T>
    implements ListAdapter
{
    private final LiveList<T> items;

    private AnVirtualListModel(LiveList items) {
        this.items = items;
    }

    public static AnVirtualListModel of(LiveList items) {
        AnVirtualListModel model = new AnVirtualListModel(items);
        return model;
    }

    public int getSize() {
        return items.size();
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
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
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
        return false;
    }
}
