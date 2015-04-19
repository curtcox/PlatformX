package se.editor;

import common.Registry;
import common.screen.Screen;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import common.uiwidget.UIComponent;
import fake.FakeForm;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.ui.EditLinkEvent;
import se.ui.EditTaggedValueEvent;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

import static org.junit.Assert.*;

public class ScreenEditorTest {

    Events events = new Events();
    TaggedValueStringMap stringMap = new TaggedValueStringMap();
    ScreenEditor testObject;

    @Before
    public void setUp() {
        FakeSERegistryLoader.load();
        Registry.put(Events.class,events);
        Registry.put(TaggedValueStringMap.class,stringMap);
        testObject = new ScreenEditor();
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
    public void sending_an_edit_command_event_causes_a_value_with_the_corresponding_tags_to_be_edited_when_registered() {
        TaggedValue value = stringMap.newValue();
        value.setTags(ScreenTags.of("whatever"));
        EditTaggedValueEvent event = new EditTaggedValueEvent(value);

        testObject.register();
        events.post(event);

        assertEquals(value.getTags(), testObject.editing.getTags());
    }

    @Test
    public void edit_makes_the_editing_frame_visible() {
        TaggedValue value = stringMap.newValue();

        testObject.edit(value);

        assertTrue(testObject.frame.isVisible());
    }

    @Test
    public void edit_puts_the_edited_text_in_the_editor() {
        TaggedValue value = stringMap.newValue();
        value.setContents("contents");
        screen().show();

        testObject.edit(value);

        assertEquals("contents", testObject.editor.getText());
    }

    Screen screen() {
        return new Screen(new FakeForm(),ScreenLink.of("")) {
            @Override
            protected UIComponent layoutForPortrait() {
                return null;
            }
        };
    }
}