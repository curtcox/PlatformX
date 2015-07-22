package an.a22.ui;

import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

public final class AnFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(PageLink link) {
        return new AnForm(link);
    }
}
