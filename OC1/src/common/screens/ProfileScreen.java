package common.screens;

import common.page.Page;
import common.page.PageLink;
import common.uiwidget.UIBorderContainer;
import common.uiwidget.UIComponent;

/**
 * For viewing and manipulating the user profile.
 */
final class ProfileScreen
    extends Page
{

    ProfileScreen(PageLink link) {
        super(link);
    }

    @Override
    public UIComponent layoutForPortrait() {
        return new UIBorderContainer();
    }
    
}
