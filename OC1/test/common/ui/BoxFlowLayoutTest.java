package common.ui;

import common.ui.BoxFlowLayout;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoxFlowLayoutTest {

    @Test
    public void can_create() {
        assertNotNull(newLayout(0, 0));
    }

    @Test
    public void willFit_returns_false_when_first_box_does_not_fit_in_layout() {
        assertFalse(newLayout(10, 10).willFitOnThisLine(new Dimension(20, 1)));
        assertFalse(newLayout(10, 10).willFitOnThisLine(new Dimension(1, 20)));
    }

    @Test
    public void willFit_returns_true_when_first_box_fits_in_layout() {
        assertTrue(newLayout(10, 10).willFitOnThisLine(new Dimension(1, 1)));
    }

    @Test
    public void getPointAtIndex_returns_minus_1_when_no_boxes_have_been_added() {
        assertEquals(-1,newLayout(1,1).getPointIndex(new Point(0,0)));
    }

    @Test
    public void addBoxToThisLine_returns_0_0_for_first_box_added() {
        BoxFlowLayout testObject = newLayout(10,10);

        Point actual = testObject.addBoxToThisLine(new Dimension(5,5));

        assertEquals(new Point(0,0),actual);
    }

    @Test
    public void addBoxToThisLine_returns_box_to_right_of_existing_box_for_second_box_added() {
        BoxFlowLayout testObject = newLayout(10,10);

        testObject.addBoxToThisLine(new Dimension(5,5));
        Point actual = testObject.addBoxToThisLine(new Dimension(1,1));

        assertEquals(new Point(5,0),actual);
    }

    @Test
    public void getPointAtIndex_returns_0_when_box_has_been_added_and_is_in_box() {
        BoxFlowLayout testObject = newLayout(10,10);

        testObject.addBoxToThisLine(new Dimension(5,5));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 1, 0);
    }

    @Test
    public void getPointAtIndex_returns_1_for_second_box_on_the_line() {
        BoxFlowLayout testObject = newLayout(10,10);

        testObject.addBoxToThisLine(new Dimension(1,1));
        testObject.addBoxToThisLine(new Dimension(1,1));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(0, 0, 0);
        assertions.pointIndex(1, 0, 1);
    }

    @Test
    public void getPointAtIndex_returns_minus_1_when_box_has_been_added_and_is_not_in_box() {
        BoxFlowLayout testObject = newLayout(10,10);

        testObject.addBoxToThisLine(new Dimension(5,5));

        BoxFlowLayoutAssertions assertions = new BoxFlowLayoutAssertions(testObject);
        assertions.pointIndex(8, 8, -1);
        assertions.pointIndex(20, 1, -1);
        assertions.pointIndex(1, 20, -1);
    }

    private BoxFlowLayout newLayout(int x, int y) {
        return new BoxFlowLayout(new Dimension(x,y));
    }
}