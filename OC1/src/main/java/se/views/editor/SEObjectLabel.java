package se.views.editor;

import se.commands.ViewObjectCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SEObjectLabel
    extends JButton
{
    private final String contents;
    private Object value;

    private SEObjectLabel(String contents) {
        this.contents = contents;
    }

    public static SEObjectLabel of(String contents) {
        final SEObjectLabel label = new SEObjectLabel(contents);
        label.setBorderPainted(false);
        label.addActionListener();
        return label;
    }

    public void set(Object value) {
        this.value = value;
        setText(contents + "=" + value);
    }

    private void addActionListener() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewObjectCommand.of().go(value);
            }
        });
    }
}
