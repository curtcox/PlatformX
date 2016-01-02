package hash;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class NumberTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void valid() {
        assertTrue(Number.isValid("1"));
    }

    @Test
    public void invalid() {
        assertFalse(Number.isValid("a"));
    }

}
