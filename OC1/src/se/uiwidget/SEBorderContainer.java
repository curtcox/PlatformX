package se.uiwidget;

import javax.swing.*;
import java.awt.*;

public final class SEBorderContainer
    extends JComponent
{

    public SEBorderContainer(JComponent component) {
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER,component);
    }

    public SEBorderContainer addNorth(Component component) {
        add(BorderLayout.NORTH,component);
        return this;
    }

    public SEBorderContainer addEast(Component component) {
        add(BorderLayout.EAST,component);
        return this;
    }
}
