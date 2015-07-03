package x.ui;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class RectangleTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void rectangle_0_0_10_10_contains() {
        Rectangle rect = new Rectangle(0,0,10,10);

        contains(rect, 1, 1);
        contains(rect, 1, 9);
        contains(rect, 9, 1);
    }

    @Test
    public void rectangle_0_0_0_0_does_not_contain_anything() {
        Rectangle rect = new Rectangle(0,0,0,0);

        doesNotContain(rect, 0, 0);
    }

    @Test
    public void rectangle_0_0_2_2_contains_1_1() {
        Rectangle rect = new Rectangle(0,0,2,2);
        contains(rect, 0, 0);
        contains(rect, 1, 1);
        contains(rect, 0, 1);
        contains(rect, 1, 0);
        doesNotContain(rect, 2, 2);
    }

    @Test
    public void rectangle_0_0_10_10_does_not_contain() {
        Rectangle rect = new Rectangle(0,0,10,10);

        doesNotContain(rect, 1, -1);
        doesNotContain(rect, -1, 1);
        doesNotContain(rect, 1, 11);
        doesNotContain(rect, 11, 1);
    }

    private void contains(Rectangle rectangle, int x, int y) {
        Point point = new Point(x,y);
        assertTrue(rectangle.contains(point));
        assertTrue(awt(rectangle).contains(awt(point)));
    }

    private void doesNotContain(Rectangle rectangle, int x, int y) {
        Point point = new Point(x,y);
        assertFalse(rectangle.contains(point));
        assertFalse(awt(rectangle).contains(awt(point)));
    }

    private java.awt.Point awt(Point point) {
        return new java.awt.Point(point.x,point.y);
    }

    private java.awt.Rectangle awt(Rectangle rect) {
        return new java.awt.Rectangle(rect.x,rect.y,rect.w,rect.h);
    }

}