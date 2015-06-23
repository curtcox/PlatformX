package fake;

import common.page.PageLink;
import common.ui.IForm;
import common.ui.IFormFactory;

public class FakeFormFactory
    implements IFormFactory
{
    public FakeForm form = new FakeForm();
    public PageLink link;

    @Override
    public IForm newForm(PageLink link) {
        this.link = link;
        form.link = link;
        return form;
    }
}
