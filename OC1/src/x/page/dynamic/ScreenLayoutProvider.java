package x.page.dynamic;

import x.page.PageLink;
import x.uiwidget.UIComponent;

public interface ScreenLayoutProvider {
    UIComponent getLayout(PageLink link, ScreenContext context);
}
