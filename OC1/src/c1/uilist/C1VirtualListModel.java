package c1.uilist;


import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import x.event.Change;
import x.event.LiveList;

/**
 * A ListModel that uses a given list to store its elements.
 */
public final class C1VirtualListModel<T>
    implements ListModel<T>
{
    private final LiveList<T> items;

    private C1VirtualListModel(LiveList items) {
        this.items = items;
    }

    public static C1VirtualListModel of(LiveList items) {
        C1VirtualListModel model = new C1VirtualListModel(items);
        return model;
    }

    public int getSize() {
        return items.size();
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public void setSelectedIndex(int i) {

    }

    @Override
    public void addDataChangedListener(final DataChangedListener dataChangedListener) {
        items.addListener(new Change.Listener() {
            @Override
            public void onChange() {
                dataChangedListener.dataChanged(0,0);
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
    public void addItem(T t) {
        items.add(t);
    }

    @Override
    public void removeItem(int i) {

    }

    @Override
    public T getItemAt(int index) {
        return items.get(index);
    }

}
