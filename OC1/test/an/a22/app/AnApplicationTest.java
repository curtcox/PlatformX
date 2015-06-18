package an.a22.app;

import android.view.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.robolectric.Robolectric.shadowOf;

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

    @Test
    public void onCreate_contentView_is_set() {
        AnApplication testObject = Robolectric.setupActivity(AnApplication.class);

        View view = shadowOf(testObject).getContentView();
        assertNotNull(view);
    }

}