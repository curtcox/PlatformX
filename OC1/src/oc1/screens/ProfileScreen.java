package oc1.screens;

import oc1.screen.Screen;

/**
 * For viewing and manipulating the user profile.
 * @author Curt
 */
final class ProfileScreen
    extends Screen
{

    ProfileScreen(Screen previous) {
        super("Profile",previous);
    }

    @Override
    protected void layoutForPortrait() {}
    
}
