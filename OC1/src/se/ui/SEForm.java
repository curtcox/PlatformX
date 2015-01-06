package se.ui;

import common.command.LoggedCommand;
import common.ui.IForm;
import common.uiwidget.UIComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SEForm
    extends JPanel
    implements IForm
{
    private final String title;
    private LoggedCommand back;
    private JButton backButton;

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
        backButton = backButton();
        panel.add(backButton,BorderLayout.WEST);
        panel.add(new JTextField(),BorderLayout.CENTER);
        return panel;
    }

    JButton backButton() {
        JButton button = new JButton("<");
        button.setEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                back.go();
            }
        });
        return button;
    }

    @Override
    public void show() {
        display().show(this);
    }

    @Override
    public void setBackCommand(LoggedCommand back) {
        this.back = back;
        backButton.setEnabled(back!=null);
    }

    @Override
    public void showBack() {
        show();
    }

    @Override
    public String getTitle() {
        return title;
    }

    private SEDisplay display() {
        return SEDisplay.of();
    }
}
