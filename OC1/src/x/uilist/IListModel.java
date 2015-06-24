package x.uilist;

import x.event.Change;

public interface IListModel
    extends Change.Source
{
    int getSize();
    void addItem(Object o);
    Object getItemAt(int i);
    int getSelectedIndex();
    void setSelectedIndex(int i);
}
