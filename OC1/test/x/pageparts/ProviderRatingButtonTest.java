package x.pageparts;

import fake.FakeUI;
import fake.FakeC1RegistryLoader;
import java.util.concurrent.Callable;
import static org.junit.Assert.*;
import org.junit.Test;
import x.uiwidget.XButton;

public class ProviderRatingButtonTest {
    
    @Test
    public void test_can_create() throws Exception {
        FakeC1RegistryLoader.load();
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
