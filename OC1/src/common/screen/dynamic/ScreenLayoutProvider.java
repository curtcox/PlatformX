package common.screen.dynamic;

import common.page.ScreenLink;
import common.uiwidget.UIComponent;

public interface ScreenLayoutProvider {
    UIComponent getLayout(ScreenLink link, ScreenContext context);
}
