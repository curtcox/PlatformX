package c1.ui;

import com.codename1.ui.Display;

public final class C1Display
    implements IDisplay
{
    @Override
    public boolean isPortrait() {
        return display().isPortrait();
    }

    @Override
    public IForm getCurrent() {
        return (IForm) display().getCurrent();
    }

    Display display() { return Display.getInstance(); }
}
