package oc1.screen;

import oc1.app.Registry;

/**
 *
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
