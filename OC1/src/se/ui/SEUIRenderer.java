package se.ui;

import common.ui.UIButton;
import common.ui.UIColumn;
import common.ui.UIComponent;
import common.ui.UILabel;

import javax.swing.*;

import static javax.swing.BoxLayout.Y_AXIS;

final class SEUIRenderer {

    static JComponent render(UIComponent layout) {
        if (layout instanceof UIButton)        { return button(layout); }
        if (layout instanceof UILabel)         { return label(layout);  }
        if (layout instanceof UIColumn)  { return column(layout);  }
        String message = layout == null ? "null" : layout.getClass().getName();
        throw new IllegalArgumentException(message);
    }

    static JPanel column(UIComponent layout) {
        UIColumn column = (UIColumn) layout;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,Y_AXIS));
        for (UIComponent component : column.components) {
            panel.add(render(component));
        }
        return panel;
    }

    static JButton button(UIComponent layout) {
        UIButton button = (UIButton) layout;
        return new JButton(button.text);
    }

    static JLabel label(UIComponent layout) {
        UILabel label = (UILabel) layout;
        return new JLabel(label.text);
    }

}
