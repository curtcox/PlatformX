package common.screen.dynamic;

import common.uiwidget.UIComponent;

public interface ScreenLayoutProvider {
    UIComponent getLayout(ScreenContext context);
}
