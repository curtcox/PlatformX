package common.screen.dynamic;

import common.screen.ScreenLink;
import common.uiwidget.UIComponent;

public interface ScreenLayoutProvider {
    UIComponent getLayout(ScreenLink link, ScreenContext context);
}
