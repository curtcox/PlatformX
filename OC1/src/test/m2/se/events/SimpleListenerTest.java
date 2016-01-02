package se.events;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class SimpleListenerTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
    }

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