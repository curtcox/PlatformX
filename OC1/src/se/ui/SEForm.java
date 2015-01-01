package se.ui;

import common.ICommand;
import c1.ui.IForm;
import common.ui.UIComponent;

import javax.swing.*;
import java.awt.*;

public final class SEForm
    extends JComponent
    implements IForm
{
    private final String title;

    public SEForm(String title) {
        this.title = title;
        setLayout(new GridLayout(1, 1));
    }

    @Override
    public void layout(UIComponent layout) {
        add(SEUIRenderer.render(layout));
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
        return title;
    }

    private SEDisplay display() {
        return SEDisplay.of();
    }
}
