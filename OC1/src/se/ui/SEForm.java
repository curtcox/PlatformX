package se.ui;

import common.command.Command;
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
    private Command back;
    private Command edit;
    private JButton backButton;
    private JButton editButton;
    private UIComponent layout;

    SEForm(String title, Command edit) {
        this.title = title;
        this.edit = edit;
        setLayout(new BorderLayout());
    }

    SEForm(String title) {
        this(title,new EditCommand());
    }

    @Override
    public void layout(UIComponent layout) {
        removeAll();
        this.layout = layout;
        add(SEUIRenderer.render(layout),BorderLayout.CENTER);
        add(navigationPanel(),BorderLayout.NORTH);
    }

    private JPanel navigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        backButton = backButton();
        editButton = editButton();
        panel.add(backButton,BorderLayout.WEST);
        panel.add(editButton,BorderLayout.EAST);
        panel.add(new JTextField(),BorderLayout.CENTER);
        return panel;
    }

    JButton editButton() {
        JButton button = new JButton("#");
        button.setEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editButtonClicked();
            }
        });
        return button;
    }

    void editButtonClicked() {
        edit.go(title,layout);
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
    public void setBackCommand(Command back) {
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
