package va.uilist;

import com.vaadin.ui.Table;
import x.event.Action;
import x.uilist.ListCellConfigurer;

final class VaUIList<T>
    extends Table
{
    private VaBasicListCellRenderer renderer;

    public void addActionListener(Action.Listener listener) {

    }

    public int getSelectedIndex() {
        return 0;
    }

    public void setRenderer(VaBasicListCellRenderer renderer) {
        this.renderer = renderer;
    }

    public static VaUIList of(VaFilterListModel model) {
        VaUIList list =  new VaUIList();
        list.setContainerDataSource(model);
        return list;
    }

    public static VaUIList of(VaFilterListModel model,ListCellConfigurer configurer) {
        VaUIList list = VaUIList.of(model);
        list.setRenderer(new VaBasicListCellRenderer(configurer));
        return list;
    }
}
