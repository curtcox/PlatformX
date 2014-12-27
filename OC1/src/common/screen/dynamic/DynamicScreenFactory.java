package common.screen.dynamic;

import common.event.StringSource;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLayout;
import common.screen.ScreenLink;
import oc1.util.Glob;
import oc1.util.GlobStringMap;
import oc1.util.StringMap;

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
    final ScreenLayout.Lookup layouts;
    
    public DynamicScreenFactory(StringMap names, ScreenController.Lookup controllers, ScreenLayout.Lookup layouts) {
        this.names = names;
        this.controllers = controllers;
        this.layouts = layouts;
    }
    
    public Screen create(ScreenLink link) {
        String name = name(link);
        if (name==null) {
            return null;
        }
        ScreenContext.Provider controller = controller(link);
        if (controller==null) {
            return null;
        }
        ScreenLayout.Provider layout = layoutProvider(link);
        if (layout==null) {
            return null;
        }
        return new DynamicScreen(name,controller,layout);
    }
    
    private String name(ScreenLink link) {
        return names.get(link.screen);
    } 
    
    private ScreenContext.Provider controller(ScreenLink link) {
        return controllers.lookup(link);
    }
    
    private ScreenLayout.Provider layoutProvider(ScreenLink link) {
        return layouts.lookup(link);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        final GlobStringMap names = new GlobStringMap();
        final GlobScreenControllerLookup controllers = new GlobScreenControllerLookup();
        final GlobScreenLayoutLookup layouts = new GlobScreenLayoutLookup();

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
