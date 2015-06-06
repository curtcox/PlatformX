package common.uilist;

public interface IListModel {

    int getSize();
    void addItem(Object o);
    Object getItemAt(int i);
    int getSelectedIndex();
    void setSelectedIndex(int i);

}
