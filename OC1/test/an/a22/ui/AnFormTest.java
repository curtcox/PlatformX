package an.a22.ui;

import android.widget.LinearLayout;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnFormTest {

    String title = random("link");
    PageLink link = PageLink.of(title);
    AnForm testObject;

    @Before
    public void setUp() {
        FakeAnRegistryLoader.load();
        testObject = new AnForm(link);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void is_IForm() {
        assertTrue(testObject instanceof IForm);
    }

    @Test
    public void title_is_set_from_constructor() {
        assertEquals(title, testObject.getTitle());
    }

    @Test
    public void link_is_set_from_constructor() {
        AnForm testObject = new AnForm(link);

        assertSame(link, testObject.getScreenLink());
    }

    @Test
    public void can_show() {
        testObject.show();
    }

    @Test
    public void there_are_0_components_when_none_have_been_added() {
        assertEquals(0, testObject.getChildCount());
    }

    @Test
    public void layout_is_idempotent() {
        XComponent layout = new XLabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(3, testObject.getChildCount());
        }
    }

    @Test
    public void form_is_LinearLayout() {
        assertTrue(testObject instanceof LinearLayout);
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}