package ios.ui;

import config.ShouldRun;
import fake.FakeIosRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.page.PageLink;
import x.ui.IDisplay;
import x.ui.IForm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class IosDisplayTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
        FakeIosRegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(IosDisplay.of());
    }

    @Test
    public void getCurrent_returns_IForm_when_IForm() throws InterruptedException {
        IosDisplay display = IosDisplay.of();
        Registry.put(IDisplay.class, display);
        new IosForm(PageLink.of("")).show();

        Thread.sleep(50);
        assertNotNull(display.getCurrent());
        assertTrue(display.getCurrent() instanceof IForm);
    }

}