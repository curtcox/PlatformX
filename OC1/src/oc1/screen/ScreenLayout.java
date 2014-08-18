package oc1.screen;

import com.codename1.ui.Component;
import com.codename1.ui.layouts.Layout;

/**
 *
 * @author Curt
 */
public final class ScreenLayout {

    final Layout layout;
    final Component[] components;
    
    public interface Provider {
        ScreenLayout getLayout(ScreenContext context);
    }
    
    public interface Lookup {
        Provider lookup(ScreenLink link);
    }

    public ScreenLayout(Layout layout, Component... components) {
        this.layout = layout;
        this.components = components;
    }
}
