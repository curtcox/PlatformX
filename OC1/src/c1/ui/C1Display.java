package c1.ui;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import common.ui.IDisplay;
import common.ui.IForm;

public final class C1Display
    implements IDisplay
{
    @Override
    public boolean isPortrait() {
        return display().isPortrait();
    }

    @Override
    public IForm getCurrent() {
        Form form = currentForm();
        return form instanceof IForm ? (IForm) form : null;
    }

    Form currentForm() {
        return display().getCurrent();
    }

    Display display() { return Display.getInstance(); }
}
