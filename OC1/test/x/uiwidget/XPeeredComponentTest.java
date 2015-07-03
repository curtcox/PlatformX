package x.uiwidget;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

public class XPeeredComponentTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void peer_is_from_constructor_arg() {
        Object expected = new Object();
        XPeeredComponent testObject = new XPeeredComponent(expected);
        Object actual = testObject.peer;

        assertSame(expected,actual);
    }
}