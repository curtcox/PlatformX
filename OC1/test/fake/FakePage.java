package fake;

import common.page.Page;
import common.page.PageLink;
import common.uiwidget.UIComponent;

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
