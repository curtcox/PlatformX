package c1.ui;

import common.screen.ScreenLink;
import common.ui.IForm;
import common.ui.IFormFactory;

/**
 * For creating new FormS.
 * This exists to provide a single spot for Form configuration.
 * See DebugForm.
 * @author Curt
 */
public final class C1FormFactory
    implements IFormFactory
{

    public IForm newForm(ScreenLink link) {
        return new C1Form(link);
    }

}
