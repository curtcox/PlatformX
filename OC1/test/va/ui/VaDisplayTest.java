package va.ui;

import config.ShouldRun;
import fake.FakeVaRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.page.PageLink;
import x.ui.IDisplay;
import x.ui.IForm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class VaDisplayTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Vaadin);
        FakeVaRegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(VaDisplay.of());
    }

    @Test
    public void getCurrent_returns_IForm_when_IForm() throws InterruptedException {
        VaDisplay display = VaDisplay.of();
        Registry.put(IDisplay.class, display);
        new VaForm(PageLink.of("")).show();

        Thread.sleep(50);
        assertNotNull(display.getCurrent());
        assertTrue(display.getCurrent() instanceof IForm);
    }

}