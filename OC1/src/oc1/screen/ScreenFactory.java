package oc1.screen;

import oc1.app.Registry;

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
            
    Screen create(ScreenLink link);

}
