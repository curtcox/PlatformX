package common.ui;

import common.command.LoggedCommand;
import common.uiwidget.UIComponent;

public interface IForm {
    void layout(UIComponent layout);

    void show();

    void setBackCommand(LoggedCommand back);

    void showBack();

    String getTitle();
}
