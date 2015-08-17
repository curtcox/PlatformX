package ios.uilist;

import org.robovm.apple.uikit.UITableView;
import x.uilist.IXListCell;

final class IosBasicListCellRenderer<T>
{
    private final IXListCell.ConfigProducer configurer;

    public IosBasicListCellRenderer(IXListCell.ConfigProducer configurer) {
        this.configurer = configurer;
    }

    public IosListCell getListCellView(UITableView tableView, T value) {
        IosListCell cell = new IosListCell();
        cell.apply(configurer.configFor(value));
        return cell;
    }
}
