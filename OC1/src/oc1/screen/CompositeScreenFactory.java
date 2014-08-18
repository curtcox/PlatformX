package oc1.screen;

/**
 *
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
        throw new IllegalArgumentException();
    }
}
