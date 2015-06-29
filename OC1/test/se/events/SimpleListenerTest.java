package se.events;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SimpleListenerTest {

    @Test
    public void implements_Listener() {
        assertTrue(new SimpleListener() instanceof Events.Listener);
    }

    @Test
    public void getLast_returns_last_event_from_onEvent() {
        Events.Event expected = new Events.Event() {};
        SimpleListener testObject = new SimpleListener();

        testObject.onEvent(expected);
        Events.Event actual = testObject.getLast();

        assertSame(expected, actual);
    }
}