package x.ui;

import x.command.XCommand;
import x.page.PageLink;
import x.uiwidget.XComponent;

/**
 * A place where ScreenS are shown.
 * Applications don't need to implement this interface.
 * This interface is used exclusively by ScreenS.
 * Form instances correspond 1-to-1 with and are used by Screen instances.
 * This interface is an abstraction of the Codenameone Form.
 * See IFormFactory, Screen, and IDisplay.
 */
public interface IForm {

    /**
     * Accept the given platform-independent layout.
     */
    void layout(XComponent layout);

    /**
     * Displays the current form.
     */
    void show();


    /**
     * Set a command that will be executed when the user indicates they want to go back to the previous screen.
     */
    void setBackCommand(XCommand back);

    /**
     * Display the current form. This method may produce a different visual transition than show in order to
     * indicate back navigation.
     */
    void showBack();

    /**
     * Return the title that was given to this form when it was created.
     */
    String getTitle();

    /**
     * Return the link used to create this form.
     */
    PageLink getScreenLink();
}
