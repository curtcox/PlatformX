package common.ui;

import common.ICommand;
import common.uiwidget.UIComponent;

public interface IForm {
    void layout(UIComponent layout);

    void show();

    void setBackCommand(ICommand back);

    void showBack();

    String getTitle();
}
