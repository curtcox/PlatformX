package oc1.screens;

import oc1.screens.RateScreen;
import fake.FakeRegistryLoader;
import oc1.ui.ActionButton;
import java.util.concurrent.Callable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class RateScreenTest {
    
    @Test
    public void test_can_create() throws Exception {
        FakeRegistryLoader.load();
        assertNotNull(createScreenOnEDT());
    }
 
    private RateScreen createScreenOnEDT() throws Exception {
        return (RateScreen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return new RateScreen(null);
            }
        });
    }
    
}
