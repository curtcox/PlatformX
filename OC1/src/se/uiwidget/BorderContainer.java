package se.uiwidget;

import javax.swing.*;
import java.awt.*;

public final class BorderContainer
    extends JComponent
{

    public BorderContainer(JComponent component) {
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER,component);
    }

    public BorderContainer addNorth(Component component) {
        add(BorderLayout.NORTH,component);
        return this;
    }

    public BorderContainer addEast(Component component) {
        add(BorderLayout.EAST,component);
        return this;
    }
}
