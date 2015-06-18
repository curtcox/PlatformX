package an.a22.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnApplicationTest {

    @Test
    public void can_create() {
        assertNotNull(new AnApplication());
    }

    @Test
    public void can_create_with_Robolectric() {
        assertNotNull(Robolectric.setupActivity(AnApplication.class));
    }
}