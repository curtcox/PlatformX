package c1.uiwidget;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;

public final class C1BorderContainer
    extends Container
{

    public C1BorderContainer(Component component) {
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER,component);
    }

    public C1BorderContainer addNorth(Component component) {
        addComponent(BorderLayout.NORTH,component);
        return this;
    }

    public C1BorderContainer addEast(Component component) {
        addComponent(BorderLayout.EAST,component);
        return this;
    }
}
