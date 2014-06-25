package oc1.screens;

import oc1.screenparts.ProviderRatingButton;
import fake.FakeRegistryLoader;
import oc1.ui.ActionButton;
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
        FakeRegistryLoader.load();
        assertNotNull(createScreenButtonOnEDT());
    }
 
    private ActionButton createScreenButtonOnEDT() throws Exception {
        return (ActionButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return ProviderRatingButton.withReturnTo(null);
            }
        });
    }

}
