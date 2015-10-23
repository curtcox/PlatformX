package se.ui;

import x.command.XCommand;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SEForm
    extends JPanel
    implements IForm
{
    private final PageLink link;
    private XCommand back;
    private final XCommand edit;
    private JButton backButton;
    private JButton editButton;
    private XComponent layout;

    SEForm(PageLink link, XCommand edit) {
        this.link = link;
        this.edit = edit;
    }

    SEForm(PageLink link) {
        this(link,new EditCommand());
    }

    @Override
    public void layout(XComponent layout) {
        removeAll();
        this.layout = layout;
        add(renderedForm());
        show();
    }

    private JComponent renderedForm() {
        return new SEBorderContainer(SEUIRenderer.render(layout))
                .north(navigationPanel());
    }

    private JPanel navigationPanel() {
        editButton = editButton();
        backButton = backButton();
        return new SEBorderContainer(address())
                .west(backButton)
                .east(editButton);
    }

    JTextField address() {
        JTextField address = new JTextField();
        address.setText(link.title());
        return address;
    }

    JButton editButton() {
        JButton button = new JButton("#");
        button.setToolTipText("Edit " + link);
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
    public void setBackCommand(XCommand back) {
        this.back = back;
        backButton.setEnabled(back!=null);
        backButton.setToolTipText(back==null ? null : back.command);
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
