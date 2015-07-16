package an.a22.uilist;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.Registry;
import x.uilist.IXListCell;

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
    public void apply(Config config) {
        firstRow.setText(config.first);
        secondRow.setText(config.second);
    }
}
