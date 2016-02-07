package se.app;

import se.events.EventListenerRecruiter;
import x.page.PageLink;
import x.screen.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * A Java SE application.
 */
public final class SEApplication {

    public static void main(String[] args) throws Exception {
        setCrossPlatformLookAndFeel();
        launchUIOnEDT();
    }

    private static void setCrossPlatformLookAndFeel() throws Exception {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }

    private static void launchUIOnEDT() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                launchApp();
            }
        });
    }

    private static void launchApp() {
        SERegistryLoader.load();
        EventListenerRecruiter.recruit();
        SEPageServer.tryToStartServer();
        show();
    }

    private static void show() {
        Screen.show(PageLink.of(""));
    }

}
