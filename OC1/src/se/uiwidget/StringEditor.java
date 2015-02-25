package se.uiwidget;

import common.event.StringChange;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

public final class StringEditor
    extends JPanel
{
    final JTextArea textArea;
    final StringChange.Listener textListener;

    StringEditor() {
        this(new JTextArea(), null, new KeyAdapter() {});
    }

    StringEditor(final StringChange.Listener textListener, KeyListener keyListener) {
        this(new JTextArea(), textListener, keyListener);
    }

    StringEditor(JTextArea textArea, final StringChange.Listener textListener, KeyListener keyListener) {
        this.textArea = textArea;
        this.textListener = textListener;
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.addKeyListener(keyListener);
        addTextListener();
    }

    void addTextListener() {
        textArea.getDocument().addDocumentListener(documentListener());
    }

    DocumentListener documentListener() {
        return new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent event) { notifyTextListener(); }
            @Override public void removeUpdate(DocumentEvent event) { notifyTextListener(); }
            @Override public void changedUpdate(DocumentEvent event) { notifyTextListener(); }
        };
    }

    void notifyTextListener() {
        textListener.onChange(new StringChange.Event(this,null,getText()));
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

}
