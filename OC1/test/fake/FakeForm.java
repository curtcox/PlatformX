package fake;

import x.command.XCommand;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;

public class FakeForm
    implements IForm
{
    public XComponent layout;
    public boolean showWasCalled;
    public boolean showBackWasCalled;
    public PageLink link;
    public XCommand backCommand;

    @Override
    public void layout(XComponent layout) {
        this.layout = layout;
    }

    @Override
    public void show() {
        showWasCalled = true;
    }

    @Override
    public void setBackCommand(XCommand backCommand) {
        this.backCommand = backCommand;
    }

    @Override
    public void showBack() {
        showBackWasCalled = true;
    }

    @Override  public String getTitle() { return link.title(); }
    @Override  public PageLink getScreenLink() { return link; }

}
