package an.a22.ui;

import common.page.ScreenLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public final class AnFormFactory
    implements IFormFactory
{
    @Override
    public IForm newForm(ScreenLink link) {
        return new AnForm(link);
    }
}
