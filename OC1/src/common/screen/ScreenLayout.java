package common.screen;

import common.ui.UIBorderLayout;
import common.ui.UIComponent;
import common.ui.UILayout;
import common.screen.dynamic.ScreenContext;

/**
 * The layout and components for a single screen.
 */
public final class ScreenLayout {

    public final UILayout layout;
    public final UIComponent[] components;

    public interface Provider {
        ScreenLayout getLayout(ScreenContext context);
    }
    
    public interface Lookup {
        Provider lookup(ScreenLink link);
    }

    public ScreenLayout(UIComponent... components) {
        this(new UIBorderLayout(),components);
    }

    public ScreenLayout(UILayout layout, UIComponent... components) {
        this.layout = layout;
        this.components = components;
    }

}
