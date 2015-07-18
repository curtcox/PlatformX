package an.a22.uiwidget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import x.Registry;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public final class AnBorderContainer
    extends RelativeLayout
{
    private final View center;
    private View north;
    private View east;
    private View west;

    AnBorderContainer(View center, Context context) {
        super(context);
        this.center = center;
        LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        setLayoutParams(params);
    }

    public static AnBorderContainer of(View center) {
        AnBorderContainer container = new AnBorderContainer(center,context());
        return container;
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
        setGravity(Gravity.CENTER);
        if (north!=null) { layoutNorth(); }
        if (east!=null)  { layoutEast();  }
        if (west!=null)  { layoutWest();  }
        layoutCenter();
        return this;
    }

    private void layoutCenter() {
        LayoutParams params = new LayoutParams(FILL_PARENT, FILL_PARENT);
        params.addRule(CENTER_VERTICAL);
        params.addRule(CENTER_HORIZONTAL);
        if (north!=null) {
            params.addRule(BELOW,north.getId());
        } else {
            params.addRule(ALIGN_TOP);
        }
        if (west!=null) {
            params.addRule(RIGHT_OF,west.getId());
        } else {
            params.addRule(ALIGN_LEFT);
        }
        if (east!=null) {
            params.addRule(LEFT_OF,east.getId());
        } else {
            params.addRule(ALIGN_RIGHT);
        }
        addView(center,params);
    }

    private void layoutWest() {
        LayoutParams params = new LayoutParams(WRAP_CONTENT, FILL_PARENT);
        params.addRule(LEFT_OF,center.getId());
        params.addRule(CENTER_VERTICAL);
        params.addRule(ALIGN_PARENT_LEFT);
        addView(west,params);
    }

    private void layoutEast() {
        LayoutParams params = new LayoutParams(WRAP_CONTENT, FILL_PARENT);
        params.addRule(RIGHT_OF,center.getId());
        params.addRule(CENTER_VERTICAL);
        params.addRule(ALIGN_PARENT_RIGHT);
        addView(east, params);
    }

    private void layoutNorth() {
        LayoutParams params = new LayoutParams(FILL_PARENT, WRAP_CONTENT);
        params.addRule(ABOVE,center.getId());
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_TOP);
        params.addRule(ALIGN_TOP);
        addView(north, params);
    }
}
