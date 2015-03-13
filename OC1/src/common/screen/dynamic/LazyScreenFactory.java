package common.screen.dynamic;

import common.screen.*;
import common.util.Mirror;
import common.util.Mirrors;
import common.util.StringMap;

/**
 * A ScreenFactory that lazily creates screens.
 * See DynamicScreenFactory.
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
        ScreenLayoutProvider layout = layoutProvider(link);
        if (layout==null) {
            return null;
        }
        return new DynamicScreen(name(link),controller(link),layout);
    }
    
    private ScreenContext.Provider controller(ScreenLink link) {
        Mirror mirror = Mirrors.of(name(link));
        return (mirror==null) ? new EmptyScreenContextProvider() : new ScreenController(mirror.getTarget());
    }
    
    private ScreenLayoutProvider layoutProvider(ScreenLink link) {
        return new DynamicScreenLayoutProvider(new StringMapStringSource(sources,name(link)));
    }

    private String name(ScreenLink link) {
        return link.tags.toString();
    }
}
