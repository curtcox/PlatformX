package x.pages;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import fake.FakeUI;
import fake.FakeXRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.page.PageLink;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class ProviderDetailsPageTest {
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        FakeXRegistryLoader.load();
    }

    @Test
    public void test_can_create() throws Exception {
        FakeC1RegistryLoader.load();
        assertNotNull(createScreenOnEDT());
    }
 
    private ProviderDetailsPage createScreenOnEDT() throws Exception {
        return (ProviderDetailsPage) FakeUI.onEDT(new Callable() {
            public Object call() throws Exception {
                return new ProviderDetailsPage(PageLink.of("Provider Details"));
            }
        });
    }
    
}
