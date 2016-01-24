package se.views.editor;

import config.ShouldRun;
import fake.FakeFormFactory;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.events.ViewObjectEvent;
import x.app.Registry;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEObjectGraphViewerTest {

    Events events = new Events();
    Object target = new Object();
    SEObjectGraphViewer viewer = new SEObjectGraphViewer();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE_UI);
        FakeSERegistryLoader.load();
        Registry.put(Events.class,events);
    }

    @Test
    public void can_create() {
        assertNotNull(SEObjectGraphViewer.of());
    }

    @Test
    public void is_singleton() {
        assertSame(SEObjectGraphViewer.of(), SEObjectGraphViewer.of());
    }

    @Test
    public void sending_a_view_command_event_causes_target_to_be_viewed_when_registered() {
        post(new ViewObjectEvent(target));

        assertEquals(target, viewer.object);
    }

    void post(ViewObjectEvent event) {
        viewer.register();
        events.post(event);
    }
}