package an.a22.app;

import android.view.View;
import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnApplicationTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Android);
    }

    @Test
    public void can_create() {
        assertNotNull(new AnApplication());
    }

    @Test
    public void can_create_with_Robolectric() {
        assertNotNull(Robolectric.setupActivity(AnApplication.class));
    }

    @Test
    public void onCreate_contentView_is_set() {
        AnApplication testObject = Robolectric.setupActivity(AnApplication.class);

//        View view = Shadows.shadowOf(testObject).getContentView();
//        assertNotNull(view);
    }

}