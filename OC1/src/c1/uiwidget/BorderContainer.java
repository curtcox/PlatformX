package c1.uiwidget;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;

public final class BorderContainer
    extends Container
{

    public BorderContainer(Component component) {
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER,component);
    }

    public BorderContainer addNorth(Component component) {
        addComponent(BorderLayout.NORTH,component);
        return this;
    }

    public BorderContainer addEast(Component component) {
        addComponent(BorderLayout.EAST,component);
        return this;
    }
}
