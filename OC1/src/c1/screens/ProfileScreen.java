package c1.screens;

import common.screen.Screen;
import common.screen.ScreenLayout;
import common.ui.UIBorderLayout;
import common.ui.UIComponent;

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
        return new UIBorderLayout();
    }
    
}
