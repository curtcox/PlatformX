package x.ui;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ColumnBoxFlowLayoutTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void can_create() {
        assertNotNull(newLayout(0));
    }

    @Test
    public void willFit_returns_false_when_1st_box_does_not_fit_in_layout() {
        assertFalse(newLayout(10).willFitOnThisLine(box(11, 1)));
        assertFalse(newLayout(10).willFitOnThisLine(box(20, 1)));
    }

    @Test
    public void willFit_returns_true_when_1st_box_fits_in_layout() {
        assertTrue(newLayout(10).willFitOnThisLine(box(1, 1)));
        assertTrue(newLayout(10).willFitOnThisLine(box(10, 1)));
    }

    @Test
    public void willFit_returns_true_when_2nd_box_fits_in_layout() {
        ColumnBoxFlowLayout testObject = newLayout(10);
        Dimension box = box(1,1);
        testObject.addBoxToThisLine(box);

        assertTrue(testObject.willFitOnThisLine(box));
    }

    @Test
    public void willFit_returns_false_when_2nd_box_does_not_fit_in_layout() {
        ColumnBoxFlowLayout testObject = newLayout(1);
        Dimension box = box(1,1);
        testObject.addBoxToThisLine(box);

        assertFalse(testObject.willFitOnThisLine(box));
    }

    @Test
    public void getPointAtIndex_returns_minus_1_when_no_boxes_have_been_added() {
        assertEquals(-1,newLayout(1).getPointIndex(new Point(0,0)));
    }

    @Test
    public void addBoxToThisLine_returns_0_0_for_first_box_added() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        Point actual = testObject.addBoxToThisLine(box(5, 5));

        assertEquals(new Point(0,0),actual);
    }

    @Test
    public void addBoxToThisLine_returns_point_directly_to_the_right_of_1st_box_added_when_adding_2nd_point() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(1,1));
        Point actual = testObject.addBoxToThisLine(box(5, 5));

        assertEquals(new Point(1,0),actual);
    }

    @Test
    public void addBoxToThisLine_returns_point_directly_to_the_right_of_2nd_box_added_when_adding_3rd_point() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(1,1));
        testObject.addBoxToThisLine(box(1,1));
        Point actual = testObject.addBoxToThisLine(box(5, 5));

        assertEquals(new Point(2,0),actual);
    }

    @Test
    public void addBoxToThisLine_returns_point_directly_to_the_right_of_1st_box_added_when_adding_second_point_on_2nd_line_after_line_with_box() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(1,1));
        testObject.startNextLineWith(box(1, 1));
        Point actual = testObject.addBoxToThisLine(box(5, 5));

        assertEquals(new Point(1,1),actual);
    }

    @Test
    public void addBoxToThisLine_returns_point_directly_to_the_right_of_1st_box_added_when_adding_second_point_on_2nd_line() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.startNextLineWith(box(1,1));
        Point actual = testObject.addBoxToThisLine(box(5, 5));

        assertEquals(new Point(1,0),actual);
    }

    @Test
    public void startNextLineWith_returns_0_0_for_first_box_added() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        Point actual = testObject.startNextLineWith(box(5, 5));

        assertEquals(new Point(0,0),actual);
    }

    @Test
    public void startNextLineWith_returns_0_2_for_second_box_added_after_first_is_2_tall() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(2,2));
        Point actual = testObject.startNextLineWith(box(1, 1));

        assertEquals(new Point(0,2),actual);
    }

    @Test
    public void startNextLineWith_returns_0_2_for_first_box_on_second_line_after_uneven_first_line_is_2_tall() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(2,2));
        testObject.addBoxToThisLine(box(1,1));
        Point actual = testObject.startNextLineWith(box(1, 1));

        assertEquals(new Point(0,2),actual);
    }

    @Test
    public void startNextLineWith_returns_0_2_for_second_box_added_after_firstNewLine_is_2_tall() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.startNextLineWith(box(2, 2));
        Point actual = testObject.startNextLineWith(box(1, 1));

        assertEquals(new Point(0,2),actual);
    }

    @Test
    public void addBoxToThisLine_returns_box_to_right_of_existing_box_for_second_box_added() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(5, 5));
        Point actual = testObject.addBoxToThisLine(box(1, 1));

        assertEquals(new Point(5,0),actual);
    }

    @Test
    public void getPointAtIndex_returns_0_when_box_has_been_added_and_is_in_box() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(5, 5));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 1, 0);
    }

    @Test
    public void getPointAtIndex_returns_1_for_second_box_on_the_line() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(new Dimension(1,1));
        testObject.addBoxToThisLine(new Dimension(1,1));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 0, 1);
    }

    @Test
    public void getPointAtIndex_returns_1_for_second_box_on_wrapped_line_when_first_box_is_1_tall() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(1,1));
        testObject.startNextLineWith(box(1, 1));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(0, 1, 1);
    }

    @Test
    public void getPointAtIndex_returns_1_for_second_box_on_wrapped_line_when_first_box_is_2_tall() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(2, 2));
        testObject.startNextLineWith(box(1, 1));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(0, 1, 0);
        assertions.pointIndex(0, 2, 1);
    }

    @Test
    public void getPointAtIndex_returns_minus_1_when_box_has_been_added_and_is_not_in_box() {
        ColumnBoxFlowLayout testObject = newLayout(10);

        testObject.addBoxToThisLine(box(5, 5));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(8, 8, -1);
        assertions.pointIndex(20, 1, -1);
        assertions.pointIndex(1, 20, -1);
    }

    private ColumnBoxFlowLayout newLayout(int width) {
        return new ColumnBoxFlowLayout(width);
    }

    Dimension box(int w, int h) {
        return new Dimension(w,h);
    }
}