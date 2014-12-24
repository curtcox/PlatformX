package oc1.screen;

import common.Registry;

/**
 * For creating ScreenS from ScreenLinkS.
 * @author Curt
 */
public interface ScreenFactory {
    
    static final ScreenFactory DEFAULT = new ScreenFactory() {
        public Screen create(ScreenLink link) {
            return Registry.get(ScreenFactory.class).create(link);
        }
    };
            
    /**
     * Return the Screen for the given link.
     * If this factory doesn't support the given link, it will generally
     * return null.
     */
    Screen create(ScreenLink link);

}
