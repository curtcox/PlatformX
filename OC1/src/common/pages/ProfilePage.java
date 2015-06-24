package common.pages;

import common.page.Page;
import common.page.PageLink;
import common.uiwidget.UIBorderContainer;
import common.uiwidget.UIComponent;

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
