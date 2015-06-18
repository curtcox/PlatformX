package an.a22.uiwidget;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import common.Registry;

public final class AnBorderContainer
    extends RelativeLayout
{
    private AnBorderContainer(Context context) {
        super(context);
    }

    public static AnBorderContainer of(View view) {
        return new AnBorderContainer(context());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    public AnBorderContainer addNorth(View component) {
        return this;
    }

    public AnBorderContainer addEast(View component) {
        return this;
    }
}
