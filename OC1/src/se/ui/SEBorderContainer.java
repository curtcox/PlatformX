package se.ui;

import javax.swing.*;
import java.awt.*;

final class SEBorderContainer
    extends JPanel
{
    SEBorderContainer(JComponent center) {
        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
    }

    SEBorderContainer east(JComponent east) {
        add(east, BorderLayout.EAST);
        return this;
    }

    SEBorderContainer west(JComponent west) {
        add(west,BorderLayout.WEST);
        return this;
    }
}
