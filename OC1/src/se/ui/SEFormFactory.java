package se.ui;

import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

public final class SEFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(PageLink link) {
        return new SEForm(link);
    }
}
