package an.a22.ui;

import common.page.PageLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public final class AnFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(PageLink link) {
        return new AnForm(link);
    }
}
