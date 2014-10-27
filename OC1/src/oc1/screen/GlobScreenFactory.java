package oc1.screen;

import oc1.util.Glob;

public abstract class GlobScreenFactory
    implements ScreenFactory
{
    final Glob glob;
    
    public GlobScreenFactory(String glob) {
        this.glob = Glob.of(glob);
    }
    
    public final Screen create(ScreenLink link) {
        if (glob.matches(link.screen)) {
            return doCreate(link);        
        }
        return null;
    }
    
    protected abstract Screen doCreate(ScreenLink link);
}
