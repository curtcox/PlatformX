package se.ui;

import javax.swing.*;
import java.awt.*;

public final class SEBorderContainer
    extends JPanel
{
    private SEBorderContainer(JComponent center) {
        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
    }

    public static SEBorderContainer of(JComponent center) {
        return new SEBorderContainer(center);
    }

    public SEBorderContainer east(JComponent east) {
        add(east, BorderLayout.EAST);
        return this;
    }

    public SEBorderContainer west(JComponent west) {
        add(west,BorderLayout.WEST);
        return this;
    }

    public SEBorderContainer north(JComponent north) {
        add(north,BorderLayout.NORTH);
        return this;
    }

    public SEBorderContainer south(JComponent south) {
        add(south,BorderLayout.SOUTH);
        return this;
    }

}
