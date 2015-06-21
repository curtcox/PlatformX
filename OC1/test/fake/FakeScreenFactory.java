package fake;

import common.screen.Page;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;

public class FakeScreenFactory
    implements ScreenFactory
{
    @Override
    public Page[] create(ScreenLink link) {
        return new Page[0];
    }
}
