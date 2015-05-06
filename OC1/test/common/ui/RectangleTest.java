package common.ui;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RectangleTest {

    @Test
    public void rectangle_0_0_10_10_contains() {
        Rectangle testObject = new Rectangle(0,0,10,10);

        assertTrue(testObject.contains(new Point(1,1)));
        assertTrue(testObject.contains(new Point(1,9)));
        assertTrue(testObject.contains(new Point(9,1)));
    }

    @Test
    public void rectangle_0_0_10_10_does_not_contain() {
        Rectangle testObject = new Rectangle(0,0,10,10);

        assertFalse(testObject.contains(new Point(1, -1)));
        assertFalse(testObject.contains(new Point(-1, 1)));
        assertFalse(testObject.contains(new Point(1, 11)));
        assertFalse(testObject.contains(new Point(11, 1)));
    }

}