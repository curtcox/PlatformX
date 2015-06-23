package fake;

import common.page.ScreenLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public class FakeFormFactory
    implements IFormFactory
{
    public FakeForm form = new FakeForm();
    public ScreenLink link;

    @Override
    public IForm newForm(ScreenLink link) {
        this.link = link;
        form.link = link;
        return form;
    }
}
