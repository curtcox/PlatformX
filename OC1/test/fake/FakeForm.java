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

    @Override
    public void layout(UIComponent layout) {
        this.layout = layout;
    }

    @Override
    public void show() {
        showWasCalled = true;
    }

    @Override
    public void setBackCommand(Command back) {

    }

    @Override
    public void showBack() {
        showBackWasCalled = true;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
