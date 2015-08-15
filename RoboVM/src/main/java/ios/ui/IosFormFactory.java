package ios.ui;

import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

public final class IosFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(PageLink link) {
        return new IosForm(link);
    }
}
