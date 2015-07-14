package an.a22.uiwidget;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import x.Registry;

public final class AnBorderContainer
    extends RelativeLayout
{
    private AnBorderContainer(View center, Context context) {
        super(context);
        addView(center);
    }

    public static AnBorderContainer of(View center) {
        return new AnBorderContainer(center,context());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    public AnBorderContainer addNorth(View north) {
        addView(north);
        return this;
    }

    public AnBorderContainer addEast(View east) {
        addView(east);
        return this;
    }
}
