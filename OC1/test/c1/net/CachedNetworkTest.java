package c1.net;

import fake.FakeC1RegistryLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CachedNetworkTest {

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(new CachedNetwork());
    }
    
}
