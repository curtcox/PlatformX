package oc1.screen;

import oc1.util.Glob;
import oc1.util.GlobStringMap;
import oc1.util.StringMap;

/**
 *
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
    
    String name(ScreenLink link) {
        return names.get(link.screen);
    } 
    
    ScreenController controller(ScreenLink link) {
        return controllers.lookup(link);
    }
    
    ScreenLayout.Provider layoutProvider(ScreenLink link) {
        return layouts.lookup(link);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {

        final GlobStringMap names = new GlobStringMap();
        final GlobScreenControllerLookup controllers = new GlobScreenControllerLookup();
        final GlobScreenLayoutLookup layouts = new GlobScreenLayoutLookup();

        public Builder map(String globString,String name,ScreenController controller,ScreenLayout.Provider layout) {
            Glob glob = new Glob(globString);
            names.add(glob,name);
            controllers.add(glob,controller);
            layouts.add(glob,layout);
            return this;
        }
        
        public ScreenFactory build() {
            return new DynamicScreenFactory(names,controllers,layouts);
        }
    }
}
