package se.uiwidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class StringMapEditor
    extends JPanel
{

    final JTextArea textArea = new JTextArea(
        "This is an editable JTextArea. " +
        "A text area is a plain text component, which means that although it can display text in any font, " +
        "all of the text is in the same font."
    );

    final class TextKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            System.out.println("text : " + textArea.getText());
        }

        @Override public void keyPressed(KeyEvent keyEvent) {}
        @Override public void keyReleased(KeyEvent keyEvent) {}
    }

    public StringMapEditor() {
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.addKeyListener(new TextKeyListener());
    }
}
