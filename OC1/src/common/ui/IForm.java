package common.ui;

import common.ICommand;

public interface IForm {
    void layout(UIComponent layout);

    void show();

    void setBackCommand(ICommand back);

    void showBack();

    String getTitle();
}
