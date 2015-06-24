package an.a22.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;
import x.Registry;
import x.ui.IDisplay;
import x.ui.IForm;

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
        activity().setContentView(form);
    }

    @Override
    public void execute(String url) {
        context().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
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

    Activity activity() {
        return Registry.get(Activity.class);
    }
}
