package an.a22.ui;

import common.ui.IDisplay;
import common.ui.IForm;

public final class AnDisplay
    implements IDisplay
{
    AnForm form;
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
        return false;
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
}
