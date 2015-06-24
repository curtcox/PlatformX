package common.pages;

import c1.screens.FakeUI;
import common.page.PageLink;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;

public class ProviderDetailsPageTest {
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
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
