package fake;

import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;

public class FakePageFactory
    implements PageFactory
{
    @Override
    public Page[] create(PageLink link) {
        return new Page[0];
    }
}
