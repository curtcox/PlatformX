package oc2.screens;

import oc1.screen.Screen;
import oc1.screen.ScreenLayout;

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
    protected ScreenLayout layoutForPortrait() {
        return new ScreenLayout();
    }
    
}
