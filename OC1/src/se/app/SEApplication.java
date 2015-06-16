package se.app;

import common.Registry;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.util.StringMap;
import mite.MiteHTTPServer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public final class SEApplication {

    public static void main(String[] args) throws Exception {
        setCrossPlatformLookAndFeel();
        launchUIOnEDT();
    }

    private static void setCrossPlatformLookAndFeel() throws Exception {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }

    private static void startServer() throws IOException {
        new MiteHTTPServer(findEmptyPort(), new StringMapRequestHandler(stringMap()));
    }

    private static int findEmptyPort() {
        return 8000;
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
        tryToStartServer();
        show();
    }

    private static void tryToStartServer() {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void show() {
        Screen.show(ScreenLink.of(""),screenFactory());
    }

    private static StringMap stringMap() {
        return Registry.get(StringMap.class);
    }

    private static ScreenFactory screenFactory() {
        return Registry.get(ScreenFactory.class);
    }

}