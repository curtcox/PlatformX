package fake;

import common.page.Page;
import common.page.ScreenLink;
import common.uiwidget.UIComponent;

public class FakePage extends Page {

    public boolean layoutForPortrait;
    public UIComponent uiComponent = new UIComponent();

    public FakePage(ScreenLink link) {
        super(link);
    }

    @Override
    public UIComponent layoutForPortrait() {
        layoutForPortrait = true;
        return uiComponent;
    }
}
