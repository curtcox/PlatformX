package an.a22.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class AnApplicationTest {

    @Test
    public void can_create() {
        assertNotNull(new AnApplication());
    }
}