package an.a22.app;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AnApplicationTest {

    @Test
    public void can_create() {
        assertNotNull(new AnApplication());
    }
}