package an.a22.ui;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import common.Registry;
import common.ui.IDisplay;
import common.ui.IForm;

public final class AnDisplay
    implements IDisplay
{
    private AnForm form;
    private static AnDisplay singleton;

    static AnDisplay of() {
        if (singleton==null) {
            singleton = newDisplay();
        }
        return singleton;
    }

    private static AnDisplay newDisplay() {
        return new AnDisplay();
    }

    @Override
    public boolean isPortrait() {
        return display().getOrientation()==0;
    }

    @Override
    public IForm getCurrent() {
        return form;
    }

    void show(AnForm form) {
        this.form = form;
    }

    @Override
    public void execute(String url) {

    }

    Display display() {
        return windowManager().getDefaultDisplay();
    }

    WindowManager windowManager() {
        return ((WindowManager) context().getSystemService(Context.WINDOW_SERVICE));
    }

    Context context() {
        return Registry.get(Context.class);
    }
}
