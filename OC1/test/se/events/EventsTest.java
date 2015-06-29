package se.events;

import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class EventsTest {

    Events.Listener listener;
    Events.Listener listener2;
    Events testObject = new Events();

    @Before
    public void setUp() {
        Mocks.init(this);
    }

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
        Events.Event expected = new Events.Event() {};
        _(); listener.onEvent(expected);

        testObject.registerListenerFor(listener,expected.getClass());
        testObject.post(expected);

        verify();
        listener.onEvent(expected);
    }

    @Test
    public void post_does_not_notify_registered_listeners_for_event_they_are_not_registered_for() {
        Events.Event expected = new Events.Event() {};
        Events.Event unexpected = new Events.Event() {};
        _(); listener.onEvent(expected);

        testObject.registerListenerFor(listener, expected.getClass());
        testObject.post(unexpected);
    }

    @Test
    public void post_notifies_registered_listeners_for_events_they_are_registered_for() {
        Events.Event event = new Events.Event() {};
        _(); listener.onEvent(event);
        Events.Event event2 = new Events.Event() {};
        _(); listener2.onEvent(event2);

        testObject.registerListenerFor(listener, event.getClass());
        testObject.registerListenerFor(listener2, event2.getClass());
        testObject.post(event);
        testObject.post(event2);

        verify();
        listener.onEvent(event);
        listener2.onEvent(event2);
    }

}