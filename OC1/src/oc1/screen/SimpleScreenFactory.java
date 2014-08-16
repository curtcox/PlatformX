package oc1.screen;

/**
 *
 * @author Curt
 */
public final class SimpleScreenFactory
    implements ScreenFactory
{
    private final ScreenFactory factory;
    
    public SimpleScreenFactory(ScreenFactory factory) {
        this.factory = factory;
    }
    
    public Screen create(ScreenLink link) {
        Screen screen = factory.create(link);
        return screen;
    }
}
