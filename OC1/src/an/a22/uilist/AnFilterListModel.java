package an.a22.uilist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import x.uilist.ListFilter;

final class AnFilterListModel<T>
    implements ListAdapter
{
    private final ListAdapter filtered;
    private DataSetObserver dataSetObserver;
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private AnFilterListModel(ListAdapter filtered) {
        this.filtered = filtered;
    }

    public static AnFilterListModel of(ListAdapter filtered) {
        AnFilterListModel model = new AnFilterListModel(filtered);
        return model;
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
        dataChanged();
    }

    public void dataChanged() {
        dataSetObserver.onChanged();
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
        this.dataSetObserver = dataSetObserver;
        filtered.registerDataSetObserver(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        int passed = 0;
        for (int i=0; i<filtered.getCount(); i++) {
            if (filter.passes(filtered.getItem(i))) {
                passed++;
            }
        }
        return passed;
    }

    @Override
    public Object getItem(int index) {
        int passed = -1;
        for (int i=0; i<filtered.getCount(); i++) {
            Object item = filtered.getItem(i);
            if (filter.passes(item)) {
                passed++;
            }
            if (passed==index) {
                return item;
            }
        }
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
        return getCount()==0;
    }
}
