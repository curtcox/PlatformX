package common.screen.dynamic;

import common.event.StringSource;
import common.page.Page;
import common.page.PageLink;
import common.page.PageTags;
import common.screen.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A PageFactory that dynamically creates screens.
 * See LazyPageFactory.
 */
public final class DynamicPageFactory
    implements PageFactory
{
    private static class Tie {
        final PageTags tags;
        final Object controller;
        final StringSource source;

        public Tie(PageTags tags, Object controller, StringSource source) {
            this.tags = tags;
            this.controller = controller;
            this.source = source;
        }
    }

    private final Tie[] ties;

    private DynamicPageFactory(Tie[] ties) {
        this.ties = ties;
    }
    
    public Page[] create(PageLink link) {
        List<Page> list = new ArrayList<Page>();
        for (Tie tie : ties) {
            if (tie.tags.matches(link)) {
                ScreenLayoutProvider layoutProvider = new DynamicScreenLayoutProvider(tie.source);
                ScreenContext.Provider controller = new ScreenController(tie.controller);
                list.add(new DynamicPage(link,controller,layoutProvider));
            }
        }
        return list.toArray(new Page[0]);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {

        List<Tie> list = new ArrayList();

        /**
         * Tie the following together.
         * @param tags used to determine if the request matches
         * @param controller creates the context and responds to actions
         * @param source for looking up the layout source
         * @return this builder for chaining
         */
        public Builder map(PageTags tags,Object controller,StringSource source) {
            list.add(new Tie(tags,controller,source));
            return this;
        }

        public PageFactory build() {
            return new DynamicPageFactory(list.toArray(new Tie[0]));
        }
    }
}
