package oc1.screen;

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
        ScreenController controller = controller(link);
        if (controller==null) {
            return null;
        }
        ScreenLayout.Provider layout = layoutProvider(link);
        if (layout==null) {
            return null;
        }
        return new LayoutScreen(name,controller,layout);
    }
    
    private ScreenController controller(ScreenLink link) {
        return new ScreenController(Mirrors.of(link.screen).getTarget());
    }
    
    private ScreenLayout.Provider layoutProvider(ScreenLink link) {
        return new DynamicScreenLayoutProvider(new StringMapStringSource(sources,link.screen));
    }
    
}
