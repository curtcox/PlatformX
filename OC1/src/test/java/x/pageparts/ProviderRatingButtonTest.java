package x.pageparts;

import config.ShouldRun;
import fake.FakeUI;
import fake.FakeXRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.uiwidget.XButton;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class ProviderRatingButtonTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void test_can_create() throws Exception {
        FakeXRegistryLoader.load();
        assertNotNull(createScreenButtonOnEDT());
    }
 
    private XButton createScreenButtonOnEDT() throws Exception {
        return (XButton) FakeUI.onEDT(new Callable() {
            public Object call() throws Exception {
                return ProviderRatingButton.of();
            }
        });
    }

}
