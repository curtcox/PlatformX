package oc1.screen;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;

/**
 * The layout and components for a single screen.
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

    public ScreenLayout(Component... components) {
        this(new BorderLayout(),components);
    }

    public ScreenLayout(Layout layout, Component... components) {
        this.layout = layout;
        this.components = components;
    }

    Component toComponent() {
        Container container = new Container();
        container.setLayout(layout);
        for (Component component : components) {
            Components.addToContainer(component, container);
        }
        return container;
    }
    
}
