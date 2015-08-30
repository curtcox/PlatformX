package va.ui;

import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

public final class VaFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(PageLink link) {
        return new VaForm(link);
    }
}
