package se.ui;

import common.ICommand;
import common.ui.IForm;
import common.uiwidget.UIComponent;

import javax.swing.*;
import java.awt.*;

public final class SEForm
    extends JPanel
    implements IForm
{
    private final String title;

    public SEForm(String title) {
        this.title = title;
        setLayout(new BorderLayout());
    }

    @Override
    public void layout(UIComponent layout) {
        removeAll();
        add(SEUIRenderer.render(layout),BorderLayout.CENTER);
        add(navigationPanel(),BorderLayout.NORTH);
    }

    private JPanel navigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JButton("<"),BorderLayout.WEST);
        panel.add(new JTextField(),BorderLayout.CENTER);
        return panel;
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
