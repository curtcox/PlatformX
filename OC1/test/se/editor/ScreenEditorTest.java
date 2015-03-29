package se.editor;

import common.Registry;
import common.screen.ScreenLink;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.ui.EditCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ScreenEditorTest {

    static Events events = new Events();
    ScreenEditor testObject;

    @Before
    public void setUp() {
        Registry.put(Events.class,events);
        testObject = ScreenEditor.of();
    }

    @Test
    public void can_create() {
        assertNotNull(ScreenEditor.of());
    }

    @Test
    public void is_singleton() {
        assertSame(ScreenEditor.of(), ScreenEditor.of());
    }

    @Test
    public void sending_an_edit_command_event_causes_a_value_with_the_corresponding_tags_to_be_edited() {
        String name = "screen name";
        ScreenLink link = ScreenLink.of(name);
        EditCommand.Event event = new EditCommand.Event(link,null);

        events.post(event);

        assertEquals(link.tags, testObject.editing.getTags());
    }

}