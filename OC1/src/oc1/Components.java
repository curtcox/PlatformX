package oc1;

import com.codename1.ui.Component;
import com.codename1.ui.Container;

public final class Components {

    private static Component removeFromParentIfAny(Component component) {
        Container parent = component.getParent();
        if (parent!=null) {
            parent.removeAll();
        }
        return component;
    }

    public static void addToContainer(Component component, Container container) {
        removeFromParentIfAny(component);
        container.addComponent(component);
    }
}
