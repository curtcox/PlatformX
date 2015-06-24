package common.screen.dynamic;

import common.event.StringSource;
import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.util.Mirror;
import common.util.Mirrors;

import java.util.ArrayList;
import java.util.List;

/**
 * A PageFactory that lazily creates screens.
 * See DynamicPageFactory.
 */
public final class LazyPageFactory
    implements PageFactory
{
    final TaggedStringSources sources;
    
    public LazyPageFactory(TaggedStringSources sources) {
        this.sources = sources;
    }
    
    public Page[] create(PageLink link) {
        List<Page> list = new ArrayList();
        for (StringSource source : sources.get(link.tags)) {
            list.add(new DynamicPage(link,controller(link), layoutProvider(source)));
        }
        return list.toArray(new Page[0]);
    }
    
    private ScreenContext.Provider controller(PageLink link) {
        Mirror mirror = Mirrors.of(tags(link));
        return (mirror==null) ? new EmptyScreenContextProvider() : new ScreenController(mirror.getTarget());
    }
    
    private ScreenLayoutProvider layoutProvider(StringSource source) {
        return new DynamicScreenLayoutProvider(source);
    }

    private String tags(PageLink link) {
        return link.tags.toString();
    }
}
