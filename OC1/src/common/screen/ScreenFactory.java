package common.screen;

import common.Registry;

/**
 * For creating ScreenS from ScreenLinkS.
 */
public interface ScreenFactory {
    
    ScreenFactory DEFAULT = new ScreenFactory() {
        public Page[] create(ScreenLink link) {
            return Registry.get(ScreenFactory.class).create(link);
        }
    };
            
    /**
     * Return the Screen for the given link.
     * If this factory doesn't support the given link,
     * it should generally return an empty array.
     */
    Page[] create(ScreenLink link);

}
