package x.pages;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.XBorderContainer;
import x.uiwidget.XComponent;

/**
 * For viewing and manipulating the user profile.
 */
final class ProfilePage
    extends Page
{

    ProfilePage(PageLink link) {
        super(link);
    }

    @Override
    public XComponent layoutForPortrait() {
        return new XBorderContainer();
    }
    
}
