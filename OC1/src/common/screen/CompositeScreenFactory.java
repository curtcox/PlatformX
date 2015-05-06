package common.screen;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;

import java.util.Arrays;

/**
 * A ScreenFactory that delegates to other ScreenFactories.
 */
public final class CompositeScreenFactory
    implements ScreenFactory
{
    private final ScreenFactory[] factories;
    
    public CompositeScreenFactory(ScreenFactory... factories) {
        this.factories = factories;
    }
    
    /**
     * Return a Screen from the first containing factory that matches the link,
     * or throw an IllegalArgumentException if none exists.
     */
    public Screen[] create(ScreenLink link) {
        for (ScreenFactory factory : factories) {
            Screen[] screen = factory.create(link);
            if (screen!=null) {
                return screen;
            }
        }
        String message = "No screen for " + link + " in " + Arrays.asList(factories);
        throw new IllegalArgumentException(message);
    }
}
