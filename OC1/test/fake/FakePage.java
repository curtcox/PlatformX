package fake;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.UIComponent;

public class FakePage extends Page {

    public boolean layoutForPortrait;
    public UIComponent uiComponent = new UIComponent();

    public FakePage(PageLink link) {
        super(link);
    }

    @Override
    public UIComponent layoutForPortrait() {
        layoutForPortrait = true;
        return uiComponent;
    }
}
