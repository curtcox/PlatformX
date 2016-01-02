package fake;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.XComponent;

public class FakePage extends Page {

    public boolean layoutForPortrait;
    public XComponent xComponent = new XComponent();

    public FakePage(PageLink link) {
        super(link);
    }

    @Override
    public XComponent layoutForPortrait() {
        layoutForPortrait = true;
        return xComponent;
    }
}
