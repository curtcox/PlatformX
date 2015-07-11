package an.a22.uilist;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.Registry;
import x.uilist.IXListCell;
import x.uiwidget.XImage;

import java.net.URI;

/**
 * A compound list cell.
 */
final class AnListCell
    extends LinearLayout
    implements IXListCell
{
    final TextView firstRow;
    final TextView secondRow;

    public AnListCell() {
        super(context());
        firstRow = new TextView(context());
        secondRow = new TextView(context());
    }

    private static Context context() {
        return Registry.get(Context.class);
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
