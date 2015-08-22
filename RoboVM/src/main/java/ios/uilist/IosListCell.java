package ios.uilist;

import org.robovm.apple.uikit.UITableViewCell;
import x.uilist.IXListCell;

/**
 * A compound list cell.
 */
final class IosListCell
    extends UITableViewCell
    implements IXListCell
{

    public IosListCell() {}

    @Override
    public void apply(Config config) {
        getTextLabel().setText(config.first);
    }


}
