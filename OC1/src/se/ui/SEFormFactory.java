package se.ui;

import common.page.PageLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public final class SEFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(PageLink link) {
        return new SEForm(link);
    }
}
