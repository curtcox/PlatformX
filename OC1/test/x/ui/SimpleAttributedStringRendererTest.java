package x.ui;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(0);

        AttributedString text = new AttributedString();
        testObject.drawText(text,partRenderer,layout);
    }

    @Test
    public void drawText_renders_single_character_that_will_fit_using_part_renderer() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(1);

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer,layout);
        
        assertEquals(1,partRenderer.parts.size());
        assertEquals(text.parts.get(0),partRenderer.parts.get(0));
    }

    @Test
    public void drawText_renders_single_character_that_will_fit_at_0_0() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(1);

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer,layout);

        assertEquals(1,partRenderer.points.size());
        assertEquals(new Point(0,0),partRenderer.points.get(0));
    }

    @Test
    public void drawText_does_not_update_boxLayout_for_an_empty_string() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(10);

        AttributedString text = new AttributedString();
        testObject.drawText(text,partRenderer,layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(0, 0, -1);
    }

    @Test
    public void drawText_updates_boxLayout_and_point_can_be_found_for_a_single_character_string() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(10);

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer, layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(0, 0,  0);
        assertions.pointIndex(0, 1, -1);
        assertions.pointIndex(1, 0, -1);
        assertions.pointIndex(1, 1, -1);
    }

    @Test
    public void drawText_updates_boxLayout_and_point_outside_of_box_is_not_matched_to_box() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(10);

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer, layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(1, 2, -1);
        assertions.pointIndex(2, 1, -1);
        assertions.pointIndex(2, 2, -1);
    }

    @Test
    public void drawText_uses_renderer_to_determine_box_size() {
        FakePartRenderer partRenderer = new FakePartRenderer() {
            @Override public Dimension size(AttributedString.Part part) {
                return new Dimension(2,2);
            }
        };
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(10);

        AttributedString text = new AttributedString("x");
        testObject.drawText(text,partRenderer, layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 1, 0);
        assertions.pointIndex(1, 3,-1);
        assertions.pointIndex(3, 1,-1);
        assertions.pointIndex(3, 3,-1);
    }

    @Test
    public void drawText_wraps_line_on_part_boundaries_when_there_is_not_enough_room_for_all_parts_on_a_line() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(1);

        AttributedString text = new AttributedString("12",Arrays.asList(part("1"),part("2")));
        testObject.drawText(text,partRenderer,layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(0, 1, 1);
        assertions.pointIndex(0, 2,-1);
        assertions.pointIndex(2, 0,-1);
        assertions.pointIndex(1, 4,-1);
        assertions.pointIndex(4, 1,-1);
    }

    @Test
    public void drawText_puts_multiple_parts_on_a_line_when_there_is_enough_room() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(10);

        AttributedString text = new AttributedString("12",Arrays.asList(part("1"),part("2")));
        testObject.drawText(text, partRenderer,layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 0, 1);
        assertions.pointIndex(2, 0,-1);
        assertions.pointIndex(0, 2,-1);
        assertions.pointIndex(1, 4,-1);
        assertions.pointIndex(4, 1,-1);
    }

    @Test
    public void drawText_puts_parts_on_following_line_after_a_new_line() {
        FakePartRenderer partRenderer = new FakePartRenderer();
        ColumnBoxFlowLayout layout = new ColumnBoxFlowLayout(10);

        AttributedString text = new AttributedString("1/n2",
                Arrays.asList(part("1"),AttributedString.NEW_LINE,part("2")));
        testObject.drawText(text, partRenderer,layout);

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(layout);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 0,-1);
        assertions.pointIndex(0, 1, 2);
    }

    private AttributedString.Part part(String text) {
        return new AttributedString.Part(null,null,null,text);
    }
}