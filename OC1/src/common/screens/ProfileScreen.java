package common.screens;

import common.page.Page;
import common.page.ScreenLink;
import common.uiwidget.UIBorderContainer;
import common.uiwidget.UIComponent;

/**
 * For viewing and manipulating the user profile.
 */
final class ProfileScreen
    extends Page
{

    ProfileScreen(ScreenLink link) {
        super(link);
    }

    @Override
    public UIComponent layoutForPortrait() {
        return new UIBorderContainer();
    }
    
}
