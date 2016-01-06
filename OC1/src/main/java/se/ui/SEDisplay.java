package se.ui;

import se.frame.FrameMeta;
import se.frame.SEFrame;
import x.ui.IDisplay;
import x.ui.IForm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class SEDisplay
    implements IDisplay
{
    final SEFrame frame;
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

    private SEDisplay(SEFrame frame) {
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

    private static SEFrame frame() {
        SEFrame frame = new SEFrame(frameMeta());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    private static FrameMeta frameMeta() {
        return new FrameMeta(
           "For displaying application pages",
           "Interact directly with the page shown. Use the navigation bar and back button to change pages.",
           SEDisplay.class
        );
    }

    void show(SEForm form) {
        this.form = form;
        Container contentPane = frame.getContentPane();
        contentPane.removeAll();
        contentPane.add(form);
        frame.pack();
    }
}
