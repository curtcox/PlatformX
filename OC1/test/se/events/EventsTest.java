package se.events;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class EventsTest {

    Events testObject = new Events();

    @Test
    public void can_create() {
        assertNotNull(new Events());
    }

    @Test
    public void post_does_nothing_when_no_registered_listeners_for_the_posted_event() {
        Events.Event expected = new Events.Event() {};

        testObject.post(expected);
    }

    @Test
    public void post_notifies_registered_listeners_for_the_posted_event() {
        SimpleListener listener = new SimpleListener();
        Events.Event expected = new Events.Event() {};
        testObject.registerListenerFor(listener,expected.getClass());
        testObject.post(expected);
        Events.Event actual = listener.getLast();
        assertSame(expected, actual);
    }

}