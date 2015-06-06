package common.uilist;

import java.util.ArrayList;
import java.util.List;

public final class CommonListModel
    implements IListModel
{
    int selectedIndex;
    List list = new ArrayList();

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public void addItem(Object o) {
        list.add(o);
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
}
