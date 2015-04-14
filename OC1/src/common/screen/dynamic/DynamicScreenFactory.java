package common.screen.dynamic;

import common.event.StringSource;
import common.screen.*;
import common.util.Glob;

import java.util.ArrayList;
import java.util.List;

/**
 * A ScreenFactory that dynamically creates screens.
 * See LazyScreenFactory.
 */
public final class DynamicScreenFactory
    implements ScreenFactory
{
    private static class Tie {
        final ScreenTags tags;
        final Object controller;
        final StringSource source;

        public Tie(ScreenTags tags, Object controller, StringSource source) {
            this.tags = tags;
            this.controller = controller;
            this.source = source;
        }
    }

    private final Tie[] ties;

    private DynamicScreenFactory(Tie[] ties) {
        this.ties = ties;
    }
    
    public Screen[] create(ScreenLink link) {
        List<Screen> list = new ArrayList<Screen>();
        for (Tie tie : ties) {
            if (tie.tags.matches(link)) {
                ScreenLayoutProvider layoutProvider = new DynamicScreenLayoutProvider(tie.source);
                ScreenContext.Provider controller = new ScreenController(tie.controller);
                list.add(new DynamicScreen(link,controller,layoutProvider));
            }
        }
        return list.toArray(new Screen[0]);
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
        public Builder map(ScreenTags tags,Object controller,StringSource source) {
            list.add(new Tie(tags,controller,source));
            return this;
        }

        public ScreenFactory build() {
            return new DynamicScreenFactory(list.toArray(new Tie[0]));
        }
    }
}
