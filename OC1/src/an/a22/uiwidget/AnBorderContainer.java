package an.a22.uiwidget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import x.Registry;

import static android.view.ViewGroup.LayoutParams.*;

/**
 * For building and adding components to a container that acts like a
 * Swing border layout.
 */
// After much struggling to do this with a relative layout, I gave up and
// switched to this nested linear layout.
public final class AnBorderContainer
        extends LinearLayout
{
    private final View center;
    private View north;
    private View east;
    private View west;
    private LinearLayout westCenterEast;

    AnBorderContainer(View center, Context context) {
        super(context);
        this.center = center;
        westCenterEast = centerRow(context);
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(stretchXY());
        setGravity(Gravity.FILL);
    }

    private static LinearLayout centerRow(Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(stretchXY());
        layout.setGravity(Gravity.FILL);
        return layout;
    }

    public static AnBorderContainer of(View center) {
        return new AnBorderContainer(center,context());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    public AnBorderContainer north(View north) {
        this.north = north;
        return this;
    }

    public AnBorderContainer east(View east) {
        this.east = east;
        return this;
    }

    public AnBorderContainer west(View west) {
        this.west = west;
        return this;
    }

    public AnBorderContainer layout() {
        if (north!=null) {
            addView(north, stretchX());
        }
        layoutCenterRow();
        return this;
    }

    private void layoutCenterRow() {
        if (west!=null) {
            westCenterEast.addView(west, stretchY());
        }
        westCenterEast.addView(center, stretchXY());
        if (east!=null) {
            westCenterEast.addView(east, stretchY());
        }
        addView(westCenterEast,stretchXY());
    }

    private static LayoutParams stretchX() {
        return new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
    }

    private static LayoutParams stretchY() {
        return new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
    }

    private static LayoutParams stretchXY() {
        return new LayoutParams(MATCH_PARENT, MATCH_PARENT);
    }

}
