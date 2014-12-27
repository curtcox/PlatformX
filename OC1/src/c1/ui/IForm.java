package c1.ui;

import common.ICommand;
import common.screen.ScreenLayout;

public interface IForm {
    void layout(ScreenLayout layout);

    void show();

    void setBackCommand(ICommand back);

    void showBack();

    String getTitle();
}
