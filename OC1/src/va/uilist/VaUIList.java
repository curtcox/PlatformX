package va.uilist;

import com.vaadin.ui.Table;
import x.Registry;
import x.event.Action;
import x.log.ILog;
import x.log.ILogManager;
import x.uilist.IXListCell;

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
        log(list + " of " + model);
        return list;
    }

    public static VaUIList of(VaFilterListModel model,IXListCell.ConfigProducer configurer) {
        VaUIList list = VaUIList.of(model);
        list.setRenderer(new VaBasicListCellRenderer(configurer));
        return list;
    }

    private static void log(String message) {
        getLog().log(message);
    }

    private static ILog getLog() {
        return Registry.get(ILogManager.class).getLog(VaUIList.class);
    }

}
