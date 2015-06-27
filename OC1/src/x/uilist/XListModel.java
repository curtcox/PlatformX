package x.uilist;

import x.event.Change;

import java.util.ArrayList;
import java.util.List;

public final class XListModel
    implements IListModel
{
    private int selectedIndex;
    private final List list = new ArrayList();
    private Change.Listener listener;

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public void addItem(Object o) {
        list.add(o);
        notifyListener();
    }

    private void notifyListener() {
        listener.onChange();
    }

    @Override
    public Object getItemAt(int i) {
        return list.get(i);
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void setSelectedIndex(int i) {
        selectedIndex = i;
    }

    @Override
    public void addListener(Change.Listener listener) {
        this.listener = listener;
    }
}
