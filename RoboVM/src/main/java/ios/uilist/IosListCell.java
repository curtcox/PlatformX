package ios.uilist;

import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UITableViewCell;
import x.uilist.IXListCell;

/**
 * A compound list cell.
 */
final class IosListCell
    extends UITableViewCell
    implements IXListCell
{
    final UILabel firstRow;
    final UILabel secondRow;

    public IosListCell() {
        firstRow = new UILabel();
        secondRow = new UILabel();
    }

    @Override
    public void apply(Config config) {
        firstRow.setText(config.first);
        secondRow.setText(config.second);
    }
}
