package oc1.screen;

import oc1.screenfactories.FilterScreenFactory;
import oc1.screenfactories.LocationSelectionScreenFactory;
import oc1.screenfactories.SearchScreenFactory;
import oc1.screens.HomeScreen;

/**
 *
 * @author Curt
 */
public final class SimpleScreenFactory
    implements ScreenFactory
{

    public Screen create(String screen, Screen previous, Object... args) {
        String lower = screen.toLowerCase();
        if ("".equals(lower)) return new HomeScreen();
        if ("searchscreen".equals(lower)) return SearchScreenFactory.withPrevious(previous);
        if ("locationselectionscreen".equals(lower)) return LocationSelectionScreenFactory.withPrevious(previous);
        if ("filterscreen".equals(lower)) return FilterScreenFactory.withPrevious(previous);
        throw new IllegalArgumentException(screen);
    }
    
}
