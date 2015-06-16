package an.a22.app;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AnApplicationTest {

    @Test @Ignore // Add Robolectric?
    public void can_create() {
        assertNotNull(new AnApplication());
    }
}