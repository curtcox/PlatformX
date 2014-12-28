package common.screen;

import common.screen.dynamic.ScreenContext;
import common.ui.UIComponent;

/**
 * The layout and components for a single screen.
 */
public interface ScreenLayout {

    public interface Provider {
        UIComponent getLayout(ScreenContext context);
    }
    
    public interface Lookup {
        Provider lookup(ScreenLink link);
    }

}
