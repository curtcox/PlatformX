package x.page.dynamic;

import x.page.PageLink;
import x.uiwidget.XComponent;

public interface ScreenLayoutProvider {
    XComponent getLayout(PageLink link, ScreenContext context);
}
