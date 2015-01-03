package c1.uiwidget;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;

public final class BoxYContainer
    extends Container
{
    public BoxYContainer(Component... components) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        for (Component component : components) {
            addComponent(component);
        }
    }
}
