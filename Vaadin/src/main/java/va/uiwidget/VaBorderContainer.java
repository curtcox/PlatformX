package va.uiwidget;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public final class VaBorderContainer
    extends VerticalLayout
{
    Component north;
    Component east;
    final Component center;
    final HorizontalLayout row = new HorizontalLayout();

    private VaBorderContainer(Component center) {
        this.center = center;
        row.addComponent(center);
        addComponent(row);
    }

    public static VaBorderContainer of(Component center) {
        return new VaBorderContainer(center);
    }

    public VaBorderContainer addNorth(Component north) {
        this.north = north;
        this.removeAllComponents();
        addComponent(north);
        addComponent(row);
        return this;
    }

    public VaBorderContainer addEast(Component east) {
        this.east = east;
        row.removeAllComponents();
        row.addComponent(east);
        row.addComponent(center);
        return this;
    }

}
