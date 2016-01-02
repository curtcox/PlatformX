package ios.app;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class IosApplicationTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
    }

    @Test
    public void can_create() {
        assertNotNull(new IosApplication());
    }

}