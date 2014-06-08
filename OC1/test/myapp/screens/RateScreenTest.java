package myapp.screens;

import myapp.screens.RateScreen;
import fake.FakeRegistryLoader;
import myapp.ui.ActionButton;
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
