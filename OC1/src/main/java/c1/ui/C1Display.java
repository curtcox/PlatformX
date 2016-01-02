package c1.ui;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import x.ui.IDisplay;
import x.ui.IForm;

/**
 * A wrapper to make a C1Display an IDisplay.
 */
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

    @Override
    public void execute(String url) {
        display().execute(url);
    }

    Form currentForm() {
        return display().getCurrent();
    }

    Display display() { return Display.getInstance(); }
}
