package common.screen.dynamic;

import common.event.StringSource;
import common.screen.*;
import common.util.Mirror;
import common.util.Mirrors;
import common.util.StringMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A ScreenFactory that lazily creates screens.
 * See DynamicScreenFactory.
 */
public final class LazyScreenFactory
    implements ScreenFactory
{
    final TaggedStringSources sources;
    
    public LazyScreenFactory(TaggedStringSources sources) {
        this.sources = sources;
    }
    
    public Screen[] create(ScreenLink link) {
        List<Screen> list = new ArrayList();
        for (StringSource source : sources.get(link.tags)) {
            list.add(new DynamicScreen(link,controller(link), layoutProvider(source)));
        }
        return new Screen[] {  };
    }
    
    private ScreenContext.Provider controller(ScreenLink link) {
        Mirror mirror = Mirrors.of(tags(link));
        return (mirror==null) ? new EmptyScreenContextProvider() : new ScreenController(mirror.getTarget());
    }
    
    private ScreenLayoutProvider layoutProvider(StringSource source) {
        return new DynamicScreenLayoutProvider(source);
    }

    private String tags(ScreenLink link) {
        return link.tags.toString();
    }
}
