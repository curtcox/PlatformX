package an.a22.uilist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import x.event.Change;
import x.event.LiveList;
import x.uilist.IXListCell;
import x.uilist.ListFilter;
import x.uilist.XListOffsets;

import java.util.ArrayList;
import java.util.List;

final class AnFilterListModel<T>
    implements ListAdapter
{
    private final LiveList filtered;
    private final AnBasicListCellRenderer renderer;
    private List<DataSetObserver> dataSetObservers = new ArrayList();
    private final XListOffsets<T> offsets;

    private AnFilterListModel(LiveList filtered,AnBasicListCellRenderer renderer) {
        this.filtered = filtered;
        this.renderer = renderer;
        this.offsets = XListOffsets.of(filtered);
    }

    public static AnFilterListModel of(LiveList filtered, IXListCell.ConfigProducer configurer) {
        AnFilterListModel model = new AnFilterListModel(filtered,new AnBasicListCellRenderer(configurer));
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
        offsets.setFilter(filter);
        dataChanged();
    }

    public void dataChanged() {
        for (DataSetObserver observer : dataSetObservers) {
            observer.onChanged();
        }
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
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
        return offsets.getSize();
    }

    @Override
    public Object getItem(int index) {
        return offsets.getElementAt(index);
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
    public View getView(int index, View view, ViewGroup viewGroup) {
        return renderer.getListCellRendererView(getItem(index));
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return getCount()==0;
    }
}
