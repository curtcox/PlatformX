package common.ui;

import org.junit.Test;
import se.uiwidget.BoxFlowLayout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class SimpleAttributedStringRendererTest {

    static class FakePartRenderer implements AttributedString.PartRenderer {

        List<AttributedString.Part> parts = new ArrayList<AttributedString.Part>();
        List<Point> points = new ArrayList<Point>();
        
        @Override
        public void renderPartAt(AttributedString.Part part, Point point) {
            parts.add(part);
            points.add(point);
        }

        @Override
        public Dimension size(AttributedString.Part part) {
            return new Dimension(1,1);
        }
    }

    SimpleAttributedStringRenderer testObject = new SimpleAttributedStringRenderer();

    @Test
    public void can_create() {
        assertNotNull(new SimpleAttributedStringRenderer());
    }

    @Test
    public void drawText_does_not_call_renderer_for_empty_string() {
        FakePartRenderer partRenderer = new FakePartRenderer() {
            public void renderPartAt(AttributedString.Part part, Point point) {
                fail();
            }
        };
        BoxFlowLayout layout = new BoxFlowLayout(new Dimension(0,0));

        AttributedString text = new AttributedString();
        testObject.drawText(text,partRenderer,layout);
    }

    @Test
    public void drawText_renders_single_character_that_will_fit_using_part_renderer() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        BoxFlowLayout layout = new BoxFlowLayout(new Dimension(1,1));

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer,layout);
        
        assertEquals(1,partRenderer.parts.size());
        assertEquals(text.parts.get(0),partRenderer.parts.get(0));
    }

    @Test
    public void drawText_renders_single_character_that_will_fit_at_0_0() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        BoxFlowLayout layout = new BoxFlowLayout(new Dimension(1,1));

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer,layout);

        assertEquals(1,partRenderer.points.size());
        assertEquals(new Point(0,0),partRenderer.points.get(0));
    }

    @Test
    public void drawText_does_not_update_boxLayout_for_an_empty_string() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        BoxFlowLayout layout = new BoxFlowLayout(new Dimension(10,10));

        AttributedString text = new AttributedString();
        testObject.drawText(text,partRenderer,layout);

        assertEquals(-1,layout.getPointIndex(new Point(0,0)));
    }

    @Test
    public void drawText_properly_updates_boxLayout_for_a_single_character_string() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        BoxFlowLayout layout = new BoxFlowLayout(new Dimension(10,10));

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer,layout);

        assertEquals(0,layout.getPointIndex(new Point(0,0)));
    }

}