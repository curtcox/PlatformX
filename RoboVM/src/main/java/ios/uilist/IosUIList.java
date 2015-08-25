package ios.uilist;

import org.robovm.apple.uikit.UITableViewController;
import org.robovm.apple.uikit.UITableViewDataSource;
import x.Registry;
import x.event.Action;
import x.log.ILog;
import x.log.ILogManager;
import x.uilist.IXListCell;

final class IosUIList<T>
    extends UITableViewController
{
    private IosBasicListCellRenderer renderer;
    private UITableViewDataSource model;

    private IosUIList(UITableViewDataSource model) {
        this.model = model;
        getTableView().setDataSource(model);
        getTableView().setScrollEnabled(true);
    }

    public void addActionListener(Action.Listener listener) {

    }

    public int getSelectedIndex() {
        return 0;
    }

    public void setRenderer(IosBasicListCellRenderer renderer) {
        this.renderer = renderer;
    }

    public static IosUIList of(UITableViewDataSource model) {
        return new IosUIList(model);
    }

    public static IosUIList of(UITableViewDataSource model,IXListCell.ConfigProducer configurer) {
        IosUIList list = IosUIList.of(model);
        list.setRenderer(new IosBasicListCellRenderer(configurer));
        return list;
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        log("viewDidLoad " + this);
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(IosUIList.class);
    }
}
