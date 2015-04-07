package common.ui;

import common.command.Command;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;

/**
 * A place where ScreenS are shown.
 * See Screen
 */
public interface IForm {
    void layout(UIComponent layout);

    void show();

    void setBackCommand(Command back);

    void showBack();

    String getTitle();

    /**
     * Return the link used to create this form.
     */
    ScreenLink getScreenLink();
}
