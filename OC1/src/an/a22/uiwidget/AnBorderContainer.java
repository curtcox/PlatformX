package an.a22.uiwidget;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import x.Registry;

public final class AnBorderContainer
    extends RelativeLayout
{
    public AnBorderContainer(View center, Context context) {
        super(context);
        addView(center);
    }

    public static AnBorderContainer of(View center) {
        return new AnBorderContainer(center,context());
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

    public AnBorderContainer north(View north) {
        addView(north);
        return this;
    }

    public AnBorderContainer east(View east) {
        addView(east);
        return this;
    }

    public AnBorderContainer west(View west) {
        addView(west);
        return this;
    }

}
