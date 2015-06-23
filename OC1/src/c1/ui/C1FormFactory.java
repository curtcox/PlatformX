package c1.ui;

import common.page.PageLink;
import common.ui.IForm;
import common.ui.IFormFactory;

/**
 * For creating new FormS.
 * This exists to provide a single spot for Form configuration.
 * See C1DebugForm.
 */
public final class C1FormFactory
    implements IFormFactory
{

    public IForm newForm(PageLink link) {
        return new C1Form(link);
    }

}
