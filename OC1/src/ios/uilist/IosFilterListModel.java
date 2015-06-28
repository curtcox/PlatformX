package ios.uilist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import x.event.Change;
import x.event.LiveList;
import x.uilist.ListFilter;

import java.util.ArrayList;
import java.util.List;

final class IosFilterListModel<T>
    implements ListAdapter
{
    private final LiveList filtered;
    private List<DataSetObserver> dataSetObservers = new ArrayList();
    private ListFilter filter = ListFilter.ALLOW_ALL;

    private IosFilterListModel(LiveList filtered) {
        this.filtered = filtered;
    }

    public static IosFilterListModel of(LiveList filtered) {
        IosFilterListModel model = new IosFilterListModel(filtered);
        model.listenForListChanges();
        return model;
    }

    private void listenForListChanges() {
        filtered.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataChanged();
            }
        });
    }

    public void setFilter(ListFilter filter) {
        this.filter = filter;
        dataChanged();
    }

    public void dataChanged() {
        for (DataSetObserver observer : dataSetObservers) {
            observer.onChanged();
        }
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
        dataSetObservers.add(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        int passed = 0;
        for (int i=0; i<filtered.size(); i++) {
            if (filter.passes(filtered.get(i))) {
                passed++;
            }
        }
        return passed;
    }

    @Override
    public Object getItem(int index) {
        int passed = -1;
        for (int i=0; i<filtered.size(); i++) {
            Object item = filtered.get(i);
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
