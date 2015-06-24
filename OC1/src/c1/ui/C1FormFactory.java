package c1.ui;

import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

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
