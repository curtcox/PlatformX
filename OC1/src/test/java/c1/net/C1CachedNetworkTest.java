package c1.net;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;

public class C1CachedNetworkTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeC1RegistryLoader.load();
    }

    @Test
    public void can_create() {
        assertNotNull(new C1CachedNetwork());
    }
    
}
