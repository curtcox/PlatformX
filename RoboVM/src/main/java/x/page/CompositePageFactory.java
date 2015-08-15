package x.page;

import java.util.Arrays;

/**
 * A PageFactory that delegates to other ScreenFactories.
 */
public final class CompositePageFactory
    implements PageFactory
{
    private final PageFactory[] factories;
    
    public CompositePageFactory(PageFactory... factories) {
        this.factories = factories;
    }
    
    /**
     * Return a Screen from the first containing factory that matches the link,
     * or throw an IllegalArgumentException if none exists.
     */
    public Page[] create(PageLink link) {
        for (PageFactory factory : factories) {
            Page[] screen = factory.create(link);
            if (screen!=null) {
                return screen;
            }
        }
        String message = "No screen for " + link + " in " + Arrays.asList(factories);
        throw new IllegalArgumentException(message);
    }
}
