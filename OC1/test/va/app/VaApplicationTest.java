package va.app;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class VaApplicationTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Vaadin);
    }

    @Test
    public void can_create() {
        assertNotNull(new VaApplication());
    }

}