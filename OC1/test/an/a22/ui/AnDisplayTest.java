package an.a22.ui;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AnDisplayTest {

    @Test
    public void can_create() {
        assertNotNull(AnDisplay.of());
    }
}