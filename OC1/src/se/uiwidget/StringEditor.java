package se.uiwidget;

import common.util.MutableString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class StringEditor
    extends JPanel
{

    private final MutableString value;
    final JTextArea textArea = new JTextArea();

    public StringEditor(MutableString value) {
        this.value = value;
        textArea.setText(value.toString());
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.addKeyListener(new TextKeyListener());
    }


    final class TextKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            value.set(textArea.getText());
            System.out.println("text : " + textArea.getText());
        }

        @Override public void keyPressed(KeyEvent keyEvent) {}
        @Override public void keyReleased(KeyEvent keyEvent) {}
    }

}
