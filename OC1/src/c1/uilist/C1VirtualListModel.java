package c1.uilist;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.EventDispatcher;
import c1.event.LiveList;

/**
 * A ListModel that uses a given list to store its elements.
 */
public final class C1VirtualListModel<T>
    implements ListModel<T>, DataChangedListener
{
    private int index;
    private final java.util.List<T> items;
    private final EventDispatcher dataListeners = new EventDispatcher();
    private final EventDispatcher selectionListeners = new EventDispatcher();

    private C1VirtualListModel(java.util.List items) {
        this.items = items;
    }

    public static C1VirtualListModel of(LiveList items) {
        C1VirtualListModel model = new C1VirtualListModel(items);
        items.addDataChangedListener(model);
        return model;
    }
    
    public T getItemAt(int index){
        return items.get(index);
    }
    
    public int getSize() {
        return items.size();
    }
    
    public int getSelectedIndex() {
        return index;
    }

    public void setSelectedIndex(int index) {
        this.index = index;
    }
    
    public void addDataChangedListener(DataChangedListener listener) {
        dataListeners.addListener(listener);
    }

    public void removeDataChangedListener(DataChangedListener listener) {
        dataListeners.removeListener(listener);
    }
    
    public void dataChanged(int type, int index) {
        dataListeners.fireDataChangeEvent(index, type);
    }

    public void addSelectionListener(SelectionListener listener) {
        selectionListeners.addListener(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
        selectionListeners.removeListener(listener);
    }
    
    public void addItem(T item)       { throw unsupported(); }   
    public void removeItem(int index) { throw unsupported(); }

    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }

}
