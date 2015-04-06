package common.screen.dynamic;

import common.event.StringSource;
import common.screen.*;
import common.util.Glob;
import common.util.GlobStringMap;
import common.util.StringMap;

/**
 * A ScreenFactory that dynamically creates screens.
 * See LazyScreenFactory.
 * @author Curt
 */
public final class DynamicScreenFactory
    implements ScreenFactory
{
    final StringMap names;
    final ScreenController.Lookup controllers;
    final ScreenLayoutLookup layouts;
    
    private DynamicScreenFactory(StringMap names, ScreenController.Lookup controllers, ScreenLayoutLookup layouts) {
        this.names = names;
        this.controllers = controllers;
        this.layouts = layouts;
    }
    
    public Screen create(ScreenLink link) {
        ScreenContext.Provider controller = controller(link);
        if (controller==null) {
            return null;
        }
        ScreenLayoutProvider layout = layoutProvider(link);
        if (layout==null) {
            return null;
        }
        return new DynamicScreen(link,controller,layout);
    }
    
    private ScreenContext.Provider controller(ScreenLink link) {
        return controllers.lookup(link);
    }
    
    private ScreenLayoutProvider layoutProvider(ScreenLink link) {
        return layouts.lookup(link);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        final GlobStringMap names = new GlobStringMap();
        final GlobScreenControllerLookup controllers = new GlobScreenControllerLookup();
        final GlobScreenLayoutLookup layouts = new GlobScreenLayoutLookup();

        /**
         * Tie the following together.
         * @param globString used to determine if the request matches
         * @param name name displayed to the user
         * @param controller creates the context and responds to actions
         * @param source for looking up the layout source
         * @return this builder for chaining
         */
        public Builder map(String globString,String name,Object controller,StringSource source) {
            Glob glob = Glob.of(globString);
            names.add(glob,name);
            controllers.add(glob,new ScreenController(controller));
            layouts.add(glob,new DynamicScreenLayoutProvider(source));
            return this;
        }
        
        public ScreenFactory build() {
            return new DynamicScreenFactory(names,controllers,layouts);
        }
    }
}
