package fake;

import x.command.Command;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.UIComponent;

public class FakeForm
    implements IForm
{
    public UIComponent layout;
    public boolean showWasCalled;
    public boolean showBackWasCalled;
    public PageLink link;
    private Command backCommand;

    @Override
    public void layout(UIComponent layout) {
        this.layout = layout;
    }

    @Override
    public void show() {
        showWasCalled = true;
    }

    @Override
    public void setBackCommand(Command backCommand) {
        this.backCommand = backCommand;
    }

    public Command getBackCommand() {
        return backCommand;
    }

    @Override
    public void showBack() {
        showBackWasCalled = true;
    }

    @Override  public String getTitle() { return link.title(); }
    @Override  public PageLink getScreenLink() { return link; }

}
