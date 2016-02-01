package se.events;

import config.ShouldRun;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class EventsTest {

    Events.Listener listener;
    Events.Listener listener2;
    Events testObject = new Events();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
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
    public void getListeners_returns_no_listeners_when_none_are_registered() {
        assertTrue(testObject.getListenersFor(Events.Event.class).isEmpty());
    }

    @Test
    public void getListeners_returns_listener_when_it_is_registered() {
        testObject.registerListenerFor(listener,Events.Event.class);
        assertSame(listener,testObject.getListenersFor(Events.Event.class).iterator().next());
    }

    @Test
    public void getListeners_does_not_returns_listener_when_it_is_registered_for_a_different_event() {
        Events.Event expected1 = new Events.Event() {};
        Events.Event expected2 = new Events.Event() {};
        testObject.registerListenerFor(listener,expected1.getClass());
        testObject.registerListenerFor(listener2,expected2.getClass());
        assertSame(listener,testObject.getListenersFor(expected1.getClass()).iterator().next());
        assertSame(listener2,testObject.getListenersFor(expected2.getClass()).iterator().next());
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