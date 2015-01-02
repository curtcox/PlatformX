package se.ui;

import common.ui.UIButton;
import common.ui.UIColumnLayout;
import common.ui.UIComponent;
import common.ui.UILabel;

import javax.swing.*;

final class SEUIRenderer {

    static JComponent render(UIComponent layout) {
        if (layout instanceof UIButton)        { return button(layout); }
        if (layout instanceof UILabel)         { return label(layout);  }
        if (layout instanceof UIColumnLayout)  { return column(layout);  }
        String message = layout == null ? "null" : layout.getClass().getName();
        throw new IllegalArgumentException(message);
    }

    static JPanel column(UIComponent layout) {
        return new JPanel();
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
