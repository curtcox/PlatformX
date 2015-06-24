package fake;

import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

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
