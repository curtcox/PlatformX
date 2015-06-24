package se.ui;

import x.command.Command;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.UIComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SEForm
    extends JPanel
    implements IForm
{
    private final PageLink link;
    private Command back;
    private Command edit;
    private JButton backButton;
    private JButton editButton;
    private UIComponent layout;

    SEForm(PageLink link, Command edit) {
        this.link = link;
        this.edit = edit;
        setLayout(new BorderLayout());
    }

    SEForm(PageLink link) {
        this(link,new EditCommand());
    }

    @Override
    public void layout(UIComponent layout) {
        removeAll();
        this.layout = layout;
        add(SEUIRenderer.render(layout), BorderLayout.CENTER);
        add(navigationPanel(), BorderLayout.NORTH);
        show();
    }

    private JPanel navigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        backButton = backButton();
        editButton = editButton();
        panel.add(backButton,BorderLayout.WEST);
        panel.add(editButton,BorderLayout.EAST);
        JTextField address = new JTextField();
        address.setText(link.tags.toString());
        panel.add(address,BorderLayout.CENTER);
        return panel;
    }

    JButton editButton() {
        JButton button = new JButton("#");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editButtonClicked();
            }
        });
        return button;
    }

    void editButtonClicked() {
        edit.go(link,layout);
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
        return link.title();
    }

    @Override
    public PageLink getScreenLink() {
        return link;
    }

    private SEDisplay display() {
        return SEDisplay.of();
    }
}
