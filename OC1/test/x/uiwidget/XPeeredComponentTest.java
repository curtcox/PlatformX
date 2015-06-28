package x.uiwidget;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class XPeeredComponentTest {

    @Test
    public void peer_is_from_constructor_arg() {
        Object expected = new Object();
        XPeeredComponent testObject = new XPeeredComponent(expected);
        Object actual = testObject.peer;

        assertSame(expected,actual);
    }
}