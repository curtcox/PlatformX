package c1.ui;

import common.ICommand;
import common.ui.UIComponent;

public interface IForm {
    void layout(UIComponent layout);

    void show();

    void setBackCommand(ICommand back);

    void showBack();

    String getTitle();
}
