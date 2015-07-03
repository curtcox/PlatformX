package c1.services;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class DistanceCalculatorTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void acos() {
        acos(-1.0);
        acos(-0.5);
        acos( 0.0);
        acos( 0.5);
        acos( 1.0);
    }
    
    private void acos(double x1) {
        double delta = 0.00000001;
        double y = DistanceCalculator.acos(x1);
        double x2 = Math.cos(y);
        assertTrue(x1 + " " + x2, Math.abs(x1-x2)<delta);
    }
}
