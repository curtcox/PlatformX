package se1.app;

import common.Registry;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;

import java.awt.*;

public final class Application {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                launch();
            }
        });
    }

    private static void launch() {
        RegistryLoader.load();
        show();
    }

    private static void show() {
        ScreenFactory factory = Registry.get(ScreenFactory.class);
        factory.create(new ScreenLink("")).show();
    }

}
