package fake;

import c1.ui.IDisplay;
import c1.ui.IForm;

public final class FakeDisplay
    implements IDisplay
{
    @Override
    public boolean isPortrait() {
        return false;
    }

    @Override
    public IForm getCurrent() {
        return null;
    }
}
