package se.ui;

import c1.ui.IDisplay;
import c1.ui.IForm;

import javax.swing.*;

public final class SEDisplay
    implements IDisplay
{
    final JFrame frame;
    SEForm form;
    private static SEDisplay singleton;

    public static SEDisplay of() {
        if (singleton==null) {
            singleton = newDisplay();
        }
        return singleton;
    }

    private static SEDisplay newDisplay() {
        return new SEDisplay(frame());
    }

    SEDisplay(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public boolean isPortrait() {
        return false;
    }

    @Override
    public IForm getCurrent() {
        return form;
    }

    private static JFrame frame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400,400);
        return frame;
    }

    void show(SEForm form) {
        this.form = form;
        frame.getContentPane().add(form);
    }
}
