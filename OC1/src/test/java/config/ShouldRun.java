package config;

import java.awt.*;

public class ShouldRun {

    // Set to run all tests
    private static final boolean All = true;
    private static final boolean Headless = isHeadless();

    public static final boolean CodenameOne = false;
    public static final boolean RoboVM = false;
    public static final boolean Android = false;
    public static final boolean JavaSE = false;
    public static final boolean JavaSE_UI = JavaSE && !Headless;
    public static final boolean Vaadin = false;

    public static final boolean X = false;
    public static final boolean Google = All || false;
    public static final boolean Hash = All || false;
    public static final boolean Mite = All || false;
    public static final boolean Mach = All || false;

    private static boolean isHeadless() {
        return graphicsEnvironment().isHeadless();
    }

    private static GraphicsEnvironment graphicsEnvironment() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment();
    }

}
