package fake;

import common.screen.ScreenLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public class FakeFormFactory
    implements IFormFactory
{
    public IForm form;
    public ScreenLink link;

    @Override
    public IForm newForm(ScreenLink link) {
        this.link = link;
        return form;
    }
}
