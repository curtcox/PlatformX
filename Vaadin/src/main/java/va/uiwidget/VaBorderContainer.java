package va.uiwidget;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

import java.util.Iterator;

public final class VaBorderContainer
    extends AbstractLayout
{
    private VaBorderContainer() {
    }

    public static VaBorderContainer of(Component view) {
        return new VaBorderContainer();
    }

    public VaBorderContainer addNorth(Component component) {
        return this;
    }

    public VaBorderContainer addEast(Component component) {
        return this;
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {

    }

    @Override
    public int getComponentCount() {
        return 0;
    }

    @Override
    public Iterator<Component> iterator() {
        return null;
    }
}
