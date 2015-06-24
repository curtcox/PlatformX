package x.pages;

import x.page.Page;
import x.page.PageLink;
import x.uiwidget.UIBorderContainer;
import x.uiwidget.UIComponent;

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
    public UIComponent layoutForPortrait() {
        return new UIBorderContainer();
    }
    
}
