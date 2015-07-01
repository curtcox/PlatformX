package ios.app;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IosApplicationTest {

    @Test
    public void can_create() {
        assertNotNull(new IosApplication());
    }

}