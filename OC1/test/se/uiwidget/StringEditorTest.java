package se.uiwidget;

import common.event.StringChange;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.Assert.*;

public class StringEditorTest {

    JTextArea textArea = new JTextArea();
    StringChangeListener stringChangeListener = new StringChangeListener();
    TextKeyListener keyListener = new TextKeyListener();
    StringEditor testObject = new StringEditor(textArea,stringChangeListener,keyListener);

    final class StringChangeListener implements StringChange.Listener {

        StringChange.Event event;

        @Override
        public void onChange(StringChange.Event event) {

            this.event = event;
        }
    }

    final class TextKeyListener extends KeyAdapter {
        boolean keyTyped;
        @Override public void keyTyped(KeyEvent keyEvent) {
            keyTyped = true;
        }
    }

    @Test
    public void can_create() {
        assertNotNull(new StringEditor(null,null));
    }

    @Test
    public void getText_returns_value_from_text_area() {
        String text = random();
        textArea.setText(text);

        assertEquals(text, testObject.getText());
    }

    @Test
    public void getText_returns_value_that_was_set() {
        String text = random();

        testObject.setText(text);
        assertEquals(text, testObject.getText());
    }

    @Test
    public void adds_given_listener_to_text_area() {
        for (KeyListener listener : textArea.getKeyListeners()) {
            if (listener==keyListener) {
                return;
            }
        }
        fail();
    }

    @Test
    public void string_listener_is_notified_when_text_changes() {
        String text = random();
        testObject.setText(text);

        StringChange.Event event = stringChangeListener.event;
        assertNotNull(event);
        assertEquals(text,       event.newValue);
        assertEquals(testObject, event.source);
    }

    private String random() {
        return toString();
    }

}
