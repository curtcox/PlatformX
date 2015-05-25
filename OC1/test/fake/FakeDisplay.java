package fake;

import common.ui.IDisplay;
import common.ui.IForm;

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

    @Override
    public void execute(String url) {}
}
