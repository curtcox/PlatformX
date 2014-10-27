package oc1.screen;

import java.util.Arrays;

/**
 * A ScreenFactory that delegates to other ScreenFactories.
 * @author Curt
 */
public final class CompositeScreenFactory
    implements ScreenFactory
{
    private final ScreenFactory[] factories;
    
    public CompositeScreenFactory(ScreenFactory... factories) {
        this.factories = factories;
    }
    
    public Screen create(ScreenLink link) {
        for (ScreenFactory factory : factories) {
            Screen screen = factory.create(link);
            if (screen!=null) {
                return screen;
            }
        }
        String message = "No screen for " + link + " in " + Arrays.asList(factories);
        throw new IllegalArgumentException(message);
    }
}
