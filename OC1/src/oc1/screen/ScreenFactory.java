package oc1.screen;

/**
 *
 * @author Curt
 */
public interface ScreenFactory {
    Screen create(String name, Screen previous, Object... args);
}
