package se.views.editor;

import se.commands.ViewObjectCommand;
import se.ui.SEBorderContainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SEObjectReferencePanel
    extends JPanel
{
    private Object value;
    private final JLabel descriptionOfObject;
    private final JButton buttonToObjectContents = new JButton();

    private SEObjectReferencePanel(String contents) {
        this.descriptionOfObject = new JLabel(contents);
    }

    public static SEObjectReferencePanel oneLine(String contents) {
        final SEObjectReferencePanel label = new SEObjectReferencePanel(contents);
        label.initAsOneLine();
        return label;
    }

    public static SEObjectReferencePanel twoLine(String contents) {
        final SEObjectReferencePanel label = new SEObjectReferencePanel(contents);
        label.initAsTwoLines();
        return label;
    }

    void initAsOneLine() {
        showButtonAsPlainText();
        add(SEBorderContainer.of(buttonToObjectContents).west(descriptionOfObject));
        addActionListener();
    }

    void initAsTwoLines() {
        showButtonAsPlainText();
        add(SEBorderContainer.of(buttonToObjectContents).north(descriptionOfObject));
        addActionListener();
    }

    void showButtonAsPlainText() {
        buttonToObjectContents.setOpaque(false);
        buttonToObjectContents.setContentAreaFilled(false);
        buttonToObjectContents.setBorderPainted(false);
    }

    public void set(Object value) {
        this.value = value;
        buttonToObjectContents.setText("" + value);
    }

    private void addActionListener() {
        buttonToObjectContents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewObjectCommand.of().go(value);
            }
        });
    }

    public Object get() {
        return value;
    }

    public String getText() {
        return buttonToObjectContents.getText();
    }
}
