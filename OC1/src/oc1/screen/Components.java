package oc1.screen;

import com.codename1.ui.Component;
import com.codename1.ui.Container;

final class Components {

    static Component removeFromParentIfAny(Component component) {
        Container parent = component.getParent();
        if (parent!=null) {
            parent.removeAll();
        }
        return component;
    }

}
