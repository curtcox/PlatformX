package fake;

import common.command.LoggedCommand;
import common.ui.IForm;
import common.uiwidget.UIComponent;

public class FakeForm
    implements IForm
{
    public UIComponent layout;

    @Override
    public void layout(UIComponent layout) {
        this.layout = layout;
    }

    @Override
    public void show() {

    }

    @Override
    public void setBackCommand(LoggedCommand back) {

    }

    @Override
    public void showBack() {

    }

    @Override
    public String getTitle() {
        return null;
    }
}
