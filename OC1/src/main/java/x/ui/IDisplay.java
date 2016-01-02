package x.ui;

/**
 * The device display singleton interface.
 * Applications don't need to implement this interface.
 * This interface is an abstraction of the Codenameone Display.
 * See IForm, Screen, and IDisplay.
 */
public interface IDisplay {

    /**
     * Return true if the current display orientation is portrait.
     */
    boolean isPortrait();

    /**
     * Return the currently showing form.
     */
    IForm getCurrent();

    /**
     * Executes the given URL on the native platform.
     */
    void execute(String url);
}
