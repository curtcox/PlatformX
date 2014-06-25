package oc1.ui;

import com.codename1.ui.Form;
import oc1.app.Registry;

/**
 *
 * @author Curt
 */
public final class FormFactory {

    public static FormFactory of() {
        return Registry.get(FormFactory.class);
    }

    public Form newForm(String title) {
        return new Form(title);
    }
}
