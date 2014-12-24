package se1.ui;

import oc1.command.ICommand;
import oc1.screen.ScreenLayout;
import oc1.ui.IForm;

import javax.swing.*;

public final class SEForm
    extends JComponent
    implements IForm
{
    final String title;

    public SEForm(String title) {
        this.title = title;
    }

    @Override
    public void layout(ScreenLayout layout) {

    }

    @Override
    public void show() {
        display().show(this);
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

    private SEDisplay display() {
        return SEDisplay.of();
    }
}
