package va.uiwidget;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class VaBorderContainer
    extends AbstractLayout
{
    final Component center;
    Component north;
    Component east;

    private VaBorderContainer(Component center) {
        this.center = center;
    }

    public static VaBorderContainer of(Component center) {
        return new VaBorderContainer(center);
    }

    public VaBorderContainer addNorth(Component north) {
        this.north = north;
        return this;
    }

    public VaBorderContainer addEast(Component east) {
        this.east = east;
        return this;
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {

    }

    @Override
    public int getComponentCount() {
        return components().size();
    }

    @Override
    public Iterator<Component> iterator() {
        return components().iterator();
    }

    List<Component> components() {
        List list = new ArrayList<Component>();
        list.add(center);
        if (north!=null) {
            list.add(north);
        }
        if (east!=null) {
            list.add(east);
        }
        return list;
    }

}
