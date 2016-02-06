package se.commands;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.events.ViewObjectEvent;
import se.util.TaggedValueStringMap;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.ui.IFormFactory;

import static mach.Mocks.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ViewObjectCommandTest {

    ViewObjectCommand command = ViewObjectCommand.of();

    ILogManager logManager;
    ILog log;
    Events events = new Events();
    Events.Listener listener;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
        Mocks.init(this);

        _(); Events.Event event = any(); listener.onEvent(event);
        _(log); logManager.getLog(any());
        Registry.put(ILogManager.class,logManager);
        Registry.put(Events.class, events);
    }

    @Test
    public void can_create() {
        assertNotNull(ViewObjectCommand.of());
    }

    @Test
    public void command_is_View_Object() {
        assertEquals("View Object",command.command);
    }

    @Test
    public void go_posts_view_object_event() {
        events.registerListenerFor(listener, ViewObjectEvent.class);
        Object objectToView = new Object();

        command.go(objectToView);

        verify();
        Events.Event event = any(); listener.onEvent(event); ViewObjectEvent arg = arg();
        assertSame(objectToView,arg.object);
    }

}