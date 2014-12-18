package oc1.ui;

import com.codename1.ui.Command;
import oc1.screen.ScreenLayout;

public interface IForm {
    void layout(ScreenLayout layout);

    void show();

    void setBackCommand(Command back);

    void showBack();
}
