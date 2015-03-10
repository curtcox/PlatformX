package se.app;

import common.Registry;
import common.net.RootStringMap;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import mite.MiteHTTPServer;
import mite.RequestHandler;
import se.util.TaggedValueStringMap;

import java.awt.*;
import java.io.IOException;

public final class Application {

    public static void main(String[] args) throws IOException {
        //startServer();
        launchUIOnEDT();
    }

    private static void startServer() throws IOException {
        TaggedValueStringMap source = null;
        RequestHandler handler = new StringMapRequestHandler(source);
        new MiteHTTPServer(findEmptyPort(),handler);
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
        RegistryLoader.load();
        show();
    }

    private static void show() {
        ScreenFactory factory = Registry.get(ScreenFactory.class);
        factory.create(ScreenLink.of("")).show();
    }

}
