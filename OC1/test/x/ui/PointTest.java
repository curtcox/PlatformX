package x.ui;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class PointTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void are_equal() {
        assertEqualPoints(new Point(0,0),new Point(0,0));
        assertEqualPoints(new Point(1,0),new Point(1,0));
        assertEqualPoints(new Point(0,1),new Point(0,1));
    }

    @Test
    public void are_not_equal() {
        assertNotEqualPoints(new Point(0, 0), new Point(0, 1));
        assertNotEqualPoints(new Point(0, 0), new Point(1, 0));
        assertNotEqualPoints(new Point(1, 1), new Point(0, 0));
        assertNotEqualPoints(new Point(1, 0), new Point(0, 1));
        assertNotEqualPoints(new Point(0, 1), new Point(1, 0));
    }

    private void assertEqualPoints(Point a, Point b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    private void assertNotEqualPoints(Point a, Point b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

}