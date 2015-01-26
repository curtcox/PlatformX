package se.uiwidget;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SEAttributedStringPartRendererTest {

    @Test
    public void can_create() {
        assertNotNull(new SEAttributedStringPartRenderer(null));
    }

}