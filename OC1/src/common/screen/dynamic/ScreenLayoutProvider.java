package common.screen.dynamic;

import common.screen.dynamic.ScreenContext;
import common.ui.UIComponent;

public interface ScreenLayoutProvider {
    UIComponent getLayout(ScreenContext context);
}
