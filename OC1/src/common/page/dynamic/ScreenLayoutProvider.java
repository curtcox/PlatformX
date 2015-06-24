package common.page.dynamic;

import common.page.PageLink;
import common.uiwidget.UIComponent;

public interface ScreenLayoutProvider {
    UIComponent getLayout(PageLink link, ScreenContext context);
}
