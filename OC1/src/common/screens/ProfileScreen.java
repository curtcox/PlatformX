package common.screens;

import common.screen.Screen;
import common.screen.ScreenLink;
import common.uiwidget.UIBorderContainer;
import common.uiwidget.UIComponent;

/**
 * For viewing and manipulating the user profile.
 */
final class ProfileScreen
    extends Screen
{

    ProfileScreen(ScreenLink link) {
        super(link);
    }

    @Override
    protected UIComponent layoutForPortrait() {
        return new UIBorderContainer();
    }
    
}
