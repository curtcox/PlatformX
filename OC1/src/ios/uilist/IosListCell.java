package ios.uilist;

import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UITableViewCell;
import x.uilist.IListCell;
import x.uiwidget.XImage;

import java.net.URI;

/**
 * A compound list cell.
 */
final class IosListCell
    extends UITableViewCell
    implements IListCell
{
    final UILabel firstRow;
    final UILabel secondRow;

    public IosListCell() {
        firstRow = new UILabel();
        secondRow = new UILabel();
    }

    @Override
    public void setFirstRowText(String text) {
        firstRow.setText(text);
    }

    @Override
    public void setSecondRowText(String text) {
        secondRow.setText(text);
    }

    @Override
    public void setIcon(XImage icon) {

    }

    @Override
    public void setIcon(URI uri) {

    }
}
