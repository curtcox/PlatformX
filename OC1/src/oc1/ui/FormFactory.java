package oc1.ui;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import oc1.app.Registry;
import oc1.command.LoggedCommand;
import oc1.screens.HomeScreen;

/**
 *
 * @author Curt
 */
public final class FormFactory {

    public static FormFactory of() {
        return Registry.get(FormFactory.class);
    }

    public Form newForm(String title) {
        Form form = DebugForm.of(title);
        form.addCommand(goHome());
        return form;
    }

    private static Command goHome() {
        return new LoggedCommand("Home") {
            @Override protected void go() {
                HomeScreen.showInitial();
            }
        };
    }

}
