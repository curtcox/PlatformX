package an.a22.ui;

import config.ShouldRun;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.Registry;
import x.page.PageLink;
import x.ui.IDisplay;
import x.ui.IForm;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnDisplayTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Android);
        FakeAnRegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(AnDisplay.of());
    }

    @Test
    public void getCurrent_returns_IForm_when_IForm() throws InterruptedException {
        AnDisplay display = AnDisplay.of();
        Registry.put(IDisplay.class, display);
        new AnForm(PageLink.of("")).show();

        Thread.sleep(50);
        assertNotNull(display.getCurrent());
        assertTrue(display.getCurrent() instanceof IForm);
    }

}