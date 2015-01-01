package se.ui;

import common.ui.UIComponent;
import common.ui.UILabel;

import javax.swing.*;

final class SEUIRenderer {

    public static JComponent render(UIComponent layout) {
        UILabel label = (UILabel) layout;
        return new JLabel(label.text);
    }
}
