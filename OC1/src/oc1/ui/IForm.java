package oc1.ui;

import common.ICommand;
import oc1.screen.ScreenLayout;

public interface IForm {
    void layout(ScreenLayout layout);

    void show();

    void setBackCommand(ICommand back);

    void showBack();

    String getTitle();
}
