package com.mycompany.myapp.screens;

import com.mycompany.fake.FakeRegistryLoader;
import java.util.concurrent.Callable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class ProviderDetailsScreenTest {
    
    @Test
    public void test_can_create() throws Exception {
        FakeRegistryLoader.load();
        assertNotNull(createScreenOnEDT());
    }
 
    private ProviderDetailsScreen createScreenOnEDT() throws Exception {
        return (ProviderDetailsScreen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return new ProviderDetailsScreen(null);
            }
        });
    }
    
}
