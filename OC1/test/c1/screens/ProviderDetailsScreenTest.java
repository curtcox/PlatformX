package c1.screens;

import fake.FakeC1RegistryLoader;
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
        FakeC1RegistryLoader.load();
    }
    
    @Test
    public void test_can_create() throws Exception {
        FakeC1RegistryLoader.load();
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
