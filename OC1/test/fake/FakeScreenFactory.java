package fake;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;

public class FakeScreenFactory
    implements ScreenFactory
{
    @Override
    public Screen[] create(ScreenLink link) {
        return new Screen[0];
    }
}
