package se.uiwidget;

import config.ShouldRun;
import org.junit.Before;
import x.ui.AttributedString;
import x.uiwidget.XAttributedText;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEAttributedTextTest {

    static class FakeRenderer implements AttributedString.Renderer {
        @Override public void render(AttributedString string) {}
    }

    static class TestAttributedText extends SEAttributedText {

        XAttributedText.SelectedEvent event;

        TestAttributedText(AttributedString text) {
            super(text);
        }

        protected void onTextSelected(XAttributedText.SelectedEvent event) {
            this.event = event;
        }

    }

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
    }

    @Test
    public void can_create() {
        assertNotNull(new SEAttributedText(new AttributedString("")));
    }

    @Test
    public void mouseClick_is_harmless_before_first_painting() {
        TestAttributedText testObject = new TestAttributedText(new AttributedString(""));

        testObject.mouseClicked(mouse(0, 0));

        assertTrue(testObject.event == null);
    }

    @Test
    public void renderer_is_not_called_for_empty_string() {
        AttributedString string = new AttributedString();
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