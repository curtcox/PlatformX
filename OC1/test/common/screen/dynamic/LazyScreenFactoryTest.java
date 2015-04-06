package common.screen.dynamic;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LazyScreenFactoryTest {

    @Test
    public void can_create() {
        assertNotNull(new LazyScreenFactory(null));
    }
}