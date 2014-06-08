package myapp.ui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;

/**
 * A container that contains components arranged by a GridLayout.
 * @author curt
 */
public final class GridContainer
    extends Container
{
    public GridContainer(int rows, int columns, Component... components) {
        setLayout(new GridLayout(rows,columns));
        for (Component component : components) {
            addComponent(component);
        }
    }
}
