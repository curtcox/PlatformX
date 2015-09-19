package an.a22.uilist;

import android.content.Context;
import android.widget.TextView;
import x.app.Registry;
import x.uilist.IXListCell;

/**
 * A compound list cell.
 */
final class AnListCell
        extends TextView
        implements IXListCell
{

    public AnListCell() {
        super(context());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    @Override
    public void apply(Config config) {
        setText(config.first + config.second);
    }

}
