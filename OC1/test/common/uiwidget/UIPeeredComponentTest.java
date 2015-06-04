package common.uiwidget;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class UIPeeredComponentTest {

    @Test
    public void peer_is_from_constructor_arg() {
        Object expected = new Object();
        UIPeeredComponent testObject = new UIPeeredComponent(expected);
        Object actual = testObject.peer;

        assertSame(expected,actual);
    }
}