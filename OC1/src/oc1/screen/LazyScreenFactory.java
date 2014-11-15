package oc1.screen;

import oc1.util.Mirror;
import oc1.util.Mirrors;
import oc1.util.StringMap;

/**
 * A ScreenFactory that lazily creates screens.
 * @author Curt
 */
public final class LazyScreenFactory
    implements ScreenFactory
{
    final StringMap sources;
    
    public LazyScreenFactory(StringMap sources) {
        this.sources = sources;
    }
    
    public Screen create(ScreenLink link) {
        String name = link.screen;
        ScreenLayout.Provider layout = layoutProvider(link);
        if (layout==null) {
            return null;
        }
        return new LayoutScreen(name,controller(link),layout);
    }
    
    private ScreenContext.Provider controller(ScreenLink link) {
        Mirror mirror = Mirrors.of(link.screen);
        return (mirror==null) ? new EmptyScreenContextProvider() : new ScreenController(mirror.getTarget());
    }
    
    private ScreenLayout.Provider layoutProvider(ScreenLink link) {
        return new DynamicScreenLayoutProvider(new StringMapStringSource(sources,link.screen));
    }
    
}
