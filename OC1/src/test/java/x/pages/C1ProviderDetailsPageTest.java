package x.pages;

import config.ShouldRun;
import fake.FakeC1UI;
import fake.FakeXRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.page.PageLink;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class C1ProviderDetailsPageTest {
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeXRegistryLoader.load();
    }

    @Test
    public void test_can_create() throws Exception {
        assertNotNull(createScreenOnEDT());
    }
 
    private ProviderDetailsPage createScreenOnEDT() throws Exception {
        return (ProviderDetailsPage) FakeC1UI.onEDT(new Callable() {
            public Object call() throws Exception {
                return new ProviderDetailsPage(PageLink.of("Provider Details"));
            }
        });
    }
    
}
