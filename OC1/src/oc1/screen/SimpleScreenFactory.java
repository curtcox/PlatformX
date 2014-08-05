package oc1.screen;

import oc1.screens.HomeScreen;

/**
 *
 * @author Curt
 */
public final class SimpleScreenFactory
    implements ScreenFactory
{

    public Screen create() {
        return new HomeScreen();
    }
    
}
