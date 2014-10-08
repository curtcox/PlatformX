package oc2.screens;

import oc2.screens.ProviderDetailsScreen;
import fake.FakeRegistryLoader;
import java.util.concurrent.Callable;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Curt
 */
public class ProviderDetailsScreenTest {
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }
    
    @Test
    public void test_can_create() throws Exception {
        FakeRegistryLoader.load();
        assertNotNull(createScreenOnEDT());
    }
 
    private ProviderDetailsScreen createScreenOnEDT() throws Exception {
        return (ProviderDetailsScreen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return new ProviderDetailsScreen();
            }
        });
    }
    
}
