package se.ui;

import common.ui.IDisplay;
import common.ui.IForm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    @Override
    public void execute(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static JFrame frame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400, 400));
        return frame;
    }

    void show(SEForm form) {
        this.form = form;
        Container contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.add(form);
        frame.pack();
    }
}
