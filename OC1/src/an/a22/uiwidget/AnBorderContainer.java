package an.a22.uiwidget;

import android.content.Context;
import android.view.View;

public final class AnBorderContainer
    extends View
{
    private AnBorderContainer(Context context) {
        super(context);
    }

    public static AnBorderContainer of(View view) {
        return null;
    }

    public AnBorderContainer addNorth(View component) {
        return this;
    }

    public AnBorderContainer addEast(View component) {
        return this;
    }
}
