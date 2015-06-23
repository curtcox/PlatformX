package fake;

import common.page.Page;
import common.screen.PageFactory;
import common.page.PageLink;

public class FakePageFactory
    implements PageFactory
{
    @Override
    public Page[] create(PageLink link) {
        return new Page[0];
    }
}
