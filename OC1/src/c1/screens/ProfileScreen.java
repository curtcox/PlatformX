package c1.screens;

import common.screen.Screen;
import common.uiwidget.UIBorderContainer;
import common.uiwidget.UIComponent;

/**
 * For viewing and manipulating the user profile.
 * @author Curt
 */
final class ProfileScreen
    extends Screen
{

    ProfileScreen() {
        super("Profile");
    }

    @Override
    protected UIComponent layoutForPortrait() {
        return new UIBorderContainer();
    }
    
}
