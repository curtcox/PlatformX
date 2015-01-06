package common.ui;

import common.command.Command;
import common.uiwidget.UIComponent;

public interface IForm {
    void layout(UIComponent layout);

    void show();

    void setBackCommand(Command back);

    void showBack();

    String getTitle();
}
