package common.screen.dynamic;

import common.event.StringSource;
import common.screen.*;
import common.util.Glob;

/**
 * A ScreenFactory that dynamically creates screens.
 * See LazyScreenFactory.
 */
public final class DynamicScreenFactory
    implements ScreenFactory
{
//    final ScreenController.Lookup controllers;
//    final ScreenLayoutLookup layouts;
    
    private DynamicScreenFactory() {
//        this.controllers = controllers;
//        this.layouts = layouts;
    }
    
    public Screen[] create(ScreenLink link) {
        ScreenContext.Provider controller = null;// = controller(link);
//        if (controller==null) {
//            return null;
//        }
        ScreenLayoutProvider layout = null;// = layoutProvider(link);
//        if (layout==null) {
//            return null;
//        }
        return new Screen[] { new DynamicScreen(link,controller,layout) };
    }
    
//    private ScreenContext.Provider controller(ScreenLink link) {
//        return controllers.lookup(link);
//    }
//
//    private ScreenLayoutProvider layoutProvider(ScreenLink link) {
//        return layouts.lookup(link);
//    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {

        /**
         * Tie the following together.
         * @param tags used to determine if the request matches
         * @param controller creates the context and responds to actions
         * @param source for looking up the layout source
         * @return this builder for chaining
         */
        public Builder map(ScreenTags tags,Object controller,StringSource source) {
//            Glob glob = Glob.of(screenSpec);
//            controllers.add(glob,new ScreenController(controller));
//            layouts.add(glob,new DynamicScreenLayoutProvider(source));
            return this;
        }

        public ScreenFactory build() {
            return new DynamicScreenFactory();
        }
    }
}
