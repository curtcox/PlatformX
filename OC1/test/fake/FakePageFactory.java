package fake;

import common.screen.Page;
import common.screen.PageFactory;
import common.screen.ScreenLink;

public class FakePageFactory
    implements PageFactory
{
    @Override
    public Page[] create(ScreenLink link) {
        return new Page[0];
    }
}
