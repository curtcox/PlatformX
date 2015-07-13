package se.uiwidget;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class SEGhostText
        implements FocusListener, DocumentListener, PropertyChangeListener
{
    private boolean isEmpty;
    private Color foregroundColor;
    private final String ghostText;
    private final JTextField textField;
    private final Color ghostColor;

    private SEGhostText(final JTextField textField, String ghostText) {
        this.textField = textField;
        this.ghostText = ghostText;
        this.ghostColor = Color.LIGHT_GRAY;
        textField.addFocusListener(this);
        registerListeners();
        updateState();
        if (!textField.hasFocus()) {
            focusLost(null);
        }
    }

    public static void installOn(JTextField textField, String ghostText) {
        new SEGhostText(textField,ghostText);
    }

    public void delete() {
        unregisterListeners();
        textField.removeFocusListener(this);
    }

    private void registerListeners() {
        textField.getDocument().addDocumentListener(this);
        textField.addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners() {
        textField.getDocument().removeDocumentListener(this);
        textField.removePropertyChangeListener("foreground", this);
    }

    private void updateState() {
        isEmpty = textField.getText().length() == 0;
        foregroundColor = textField.getForeground();
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (isEmpty) {
            unregisterListeners();
            try {
                textField.setText("");
                textField.setForeground(foregroundColor);
            } finally {
                registerListeners();
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (isEmpty) {
            unregisterListeners();
            try {
                textField.setText(ghostText);
                textField.setForeground(ghostColor);
            } finally {
                registerListeners();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateState();
    }

}
