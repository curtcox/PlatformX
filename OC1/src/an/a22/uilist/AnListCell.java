package an.a22.uilist;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import x.Registry;
import x.uilist.IXListCell;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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
        configureLayout();
    }

    private void configureLayout() {
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(stretchXY());
        setGravity(Gravity.CENTER);
        addView(firstRow,stretchX());
        addView(secondRow,stretchX());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    @Override
    public void apply(Config config) {
        firstRow.setText(config.first);
        secondRow.setText(config.second);
    }

    private static LayoutParams stretchXY() {
        return new LayoutParams(MATCH_PARENT, MATCH_PARENT);
    }

    private static LayoutParams stretchX() {
        return new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
    }

}
