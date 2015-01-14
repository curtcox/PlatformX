package se.uiwidget;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BoxFlowLayoutTest {

    @Test
    public void can_create() {
        assertNotNull(new BoxFlowLayout(new Dimension(0,0)));
    }

    @Test
    public void willFit_returns_false_when_first_box_does_not_fit_in_layout() {
        assertFalse(new BoxFlowLayout(new Dimension(10, 10)).willFitOnThisLine(new Dimension(20, 1)));
        assertFalse(new BoxFlowLayout(new Dimension(10, 10)).willFitOnThisLine(new Dimension(1, 20)));
    }

    @Test
    public void willFit_returns_true_when_first_box_fits_in_layout() {
        assertTrue(new BoxFlowLayout(new Dimension(10,10)).willFitOnThisLine(new Dimension(1, 1)));
    }
}