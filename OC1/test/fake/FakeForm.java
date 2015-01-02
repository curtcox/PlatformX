package fake;

import common.ICommand;
import common.ui.IForm;
import common.ui.UIComponent;

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
    public void setBackCommand(ICommand back) {

    }

    @Override
    public void showBack() {

    }

    @Override
    public String getTitle() {
        return null;
    }
}
