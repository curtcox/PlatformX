package se.uiwidget;

import common.ui.AttributedString;
import common.ui.AttributedString.PartRenderer;
import common.uiwidget.UIAttributedText;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.Assert.*;

public class SEAttributedTextTest {

    static class FakeRenderer implements PartRenderer {
        @Override public void renderPartAt(AttributedString.Part part, Point point) {}
        @Override public Dimension size(AttributedString.Part part) { return new Dimension(part.size,1); }
    }

    static class TestAttributedText extends SEAttributedText {

        UIAttributedText.SelectedEvent event;

        TestAttributedText(AttributedString text) {
            super(text);
        }

        protected void onTextSelected(UIAttributedText.SelectedEvent event) {
            this.event = event;
        }

    }

    @Test
    public void can_create() {
        assertNotNull(new SEAttributedText(new AttributedString("")));
    }

    @Test
    public void mouseClick_does_not_trigger_onTextSelected_when_there_is_no_text_rendered() {
        TestAttributedText testObject = new TestAttributedText(new AttributedString(""));
        FakeRenderer renderer = new FakeRenderer();
        testObject.drawText(renderer);
        testObject.mouseClicked(mouse(0, 0));

        assertTrue(testObject.event == null);
    }

    @Test
    public void mouseClick_does_not_trigger_onTextSelected_when_rendered_text_is_empty() {
        AttributedString string = new AttributedString("");
        TestAttributedText testObject = new TestAttributedText(string);

        FakeRenderer renderer = new FakeRenderer();
        testObject.drawText(renderer);
        testObject.mouseClicked(mouse(0,0));

        assertTrue(testObject.event==null);
    }

    @Test
    public void renderer_is_not_called_for_empty_string() {
        AttributedString string = new AttributedString("");
        TestAttributedText testObject = new TestAttributedText(string);
        FakeRenderer renderer = new FakeRenderer() {
            public void renderPartAt(AttributedString.Part part, Point point) {
                fail();
            }
        };
        testObject.drawText(renderer);
    }

    MouseEvent mouse(int x, int y) {
        return new MouseEvent(new JPanel(),0,0,0,x,y,0,false);
    }

}