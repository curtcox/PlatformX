package ios.uilist;

import org.robovm.apple.uikit.UITableViewController;
import org.robovm.apple.uikit.UITableViewDataSource;
import x.event.Action;
import x.uilist.ListCellConfigurer;
import x.uilist.UIList;

final class IosUIList<T>
    extends UITableViewController
    implements UIList
{
    private IosBasicListCellRenderer renderer;

    @Override
    public void addActionListener(Action.Listener listener) {

    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    public void setRenderer(IosBasicListCellRenderer renderer) {
        this.renderer = renderer;
    }

    public static IosUIList of(UITableViewDataSource model) {
        return null;
    }

    public static UIList of(UITableViewDataSource model,ListCellConfigurer configurer) {
        IosUIList list = IosUIList.of(model);
        list.setRenderer(new IosBasicListCellRenderer(configurer));
        return list;
    }
}
