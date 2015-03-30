package se.editor;

import common.Registry;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.ui.EditCommand;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

import static org.junit.Assert.*;

public class ScreenEditorTest {

    static Events events = new Events();
    TaggedValueStringMap stringMap = new TaggedValueStringMap();
    ScreenEditor testObject;

    @Before
    public void setUp() {
        Registry.put(Events.class,events);
        Registry.put(TaggedValueStringMap.class,stringMap);
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

    @Test
    public void edit_makes_the_editing_frame_visible() {
        ScreenTags tags = ScreenTags.of("whatever");

        testObject.edit(tags);

        assertTrue(testObject.frame.isVisible());
    }

    @Test
    public void edit_puts_the_edited_text_in_the_editor() {
        ScreenTags tags = ScreenTags.of("whatever");
        TaggedValue value = stringMap.newValue();
        value.setTags(tags);
        value.setContents("contents");

        testObject.edit(tags);

        assertEquals("contents", testObject.editor.getText());
    }

}