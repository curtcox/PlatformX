package c1.screens;

import c1.screenparts.ProviderRatingButton;
import fake.FakeC1RegistryLoader;
import common.screenparts.ActionButton;
import java.util.concurrent.Callable;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ProviderRatingButtonTest {
    
    @Test
    public void test_can_create() throws Exception {
        FakeC1RegistryLoader.load();
        assertNotNull(createScreenButtonOnEDT());
    }
 
    private ActionButton createScreenButtonOnEDT() throws Exception {
        return (ActionButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return ProviderRatingButton.of();
            }
        });
    }

}
