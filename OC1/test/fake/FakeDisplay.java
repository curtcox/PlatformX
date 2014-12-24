package fake;

import oc1.ui.IDisplay;
import oc1.ui.IForm;

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
