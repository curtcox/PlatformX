package se.uiwidget;

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
    public void getPointAtIndex_returns_0_when_box_has_been_added() {
        BoxFlowLayout testObject = newLayout(10,10);
        testObject.addBoxToThisLine(new Dimension(5,5));
        assertEquals(0,testObject.getPointIndex(new Point(0,0)));
    }

    private BoxFlowLayout newLayout(int x, int y) {
        return new BoxFlowLayout(new Dimension(x,y));
    }
}