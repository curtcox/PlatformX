package c1.net;

import fake.FakeRegistryLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CachedNetworkTest {

    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(new CachedNetwork());
    }
    
}
