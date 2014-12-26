package oc1.screen;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;

/**
 * The layout and components for a single screen.
 */
public final class ScreenLayout {

    public final Layout layout;
    public final Object[] components;

    public interface Provider {
        ScreenLayout getLayout(ScreenContext context);
    }
    
    public interface Lookup {
        Provider lookup(ScreenLink link);
    }

    public ScreenLayout(Object... components) {
        this(new BorderLayout(),components);
    }

    public ScreenLayout(Layout layout, Object... components) {
        this.layout = layout;
        this.components = components;
    }

}
