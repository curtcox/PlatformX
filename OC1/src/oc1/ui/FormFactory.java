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
public final class FormFactory
    implements IFormFactory
{

    public IForm newForm(String title) {
        return new C1Form(title);
    }

}
