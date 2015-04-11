package fake;

import common.screen.Screen;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;

public class FakeScreen extends Screen {

    public boolean layoutForPortrait;
    public UIComponent uiComponent;

    public FakeScreen(ScreenLink link) {
        super(link);
    }

    @Override
    protected UIComponent layoutForPortrait() {
        layoutForPortrait = true;
        return uiComponent;
    }
}
