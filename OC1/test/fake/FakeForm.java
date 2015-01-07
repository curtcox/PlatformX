package fake;

import common.command.Command;
import common.ui.IForm;
import common.uiwidget.UIComponent;

public class FakeForm
    implements IForm
{
    public UIComponent layout;
    public boolean showWasCalled;
    public boolean showBackWasCalled;
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

    @Override
    public void showBack() {
        showBackWasCalled = true;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public Command getBackCommand() {
        return backCommand;
    }
}
