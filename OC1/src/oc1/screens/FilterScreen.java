package oc1.screens;

import oc1.screen.Screen;

/**
 * For filtering provider types.
 * @author Curt
 */
final class FilterScreen
    extends Screen
{

    FilterScreen(Screen previous) {
        super("Filter",previous);
    }

    @Override
    protected void layoutForPortrait() {}
    
}
