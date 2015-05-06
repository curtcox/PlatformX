package c1.screens;

import common.screen.Screen;
import common.screen.ScreenLink;
import common.uiwidget.UIBorderContainer;
import common.uiwidget.UIComponent;

/**
 * For viewing and manipulating the user profile.
 * @author Curt
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
