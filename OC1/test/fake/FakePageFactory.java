package fake;

import common.page.Page;
import common.screen.PageFactory;
import common.page.ScreenLink;

public class FakePageFactory
    implements PageFactory
{
    @Override
    public Page[] create(ScreenLink link) {
        return new Page[0];
    }
}
