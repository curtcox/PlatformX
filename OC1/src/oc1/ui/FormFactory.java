package oc1.ui;

import com.codename1.ui.*;
import oc1.app.Registry;
import oc1.command.LoggedCommand;
import oc1.screen.*;

/**
 * For creating new FormS.
 * This exists to provide a single spot for Form configuration.
 * See DebugForm.
 * @author Curt
 */
public final class FormFactory {

    public static FormFactory of() {
        return Registry.get(FormFactory.class);
    }

    public Form newForm(String title) {
        Form form = new Form(title);
        form.addCommand(goHome());
        return form;
    }

    private static Command goHome() {
        return new LoggedCommand("Home") {
            @Override protected void go() {
                ScreenFactory.DEFAULT.create(new ScreenLink("")).show();
            }
        };
    }

}
