package se.editor;

import config.ShouldRun;
import x.Registry;
import x.page.Page;
import x.page.PageLink;
import x.page.PageTags;
import x.screen.Screen;
import x.ui.IFormFactory;
import x.uiwidget.XComponent;
import fake.FakeFormFactory;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.ui.EditTaggedValueEvent;
import se.util.SimpleTaggedValueStringMap;
import se.util.TaggedValue;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ScreenEditorTest {

    Events events = new Events();
    SimpleTaggedValueStringMap stringMap = new SimpleTaggedValueStringMap();
    ScreenEditor testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
        FakeSERegistryLoader.load();
        Registry.put(Events.class,events);
        Registry.put(SimpleTaggedValueStringMap.class,stringMap);
        Registry.put(IFormFactory.class,new FakeFormFactory());
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
        value.setTags(PageTags.of("whatever"));
        EditTaggedValueEvent event = new EditTaggedValueEvent(value);

        testObject.register();
        events.post(event);

        assertEquals(value.getTags(), testObject.editing.getTags());
    }

    @Test
    public void edit_makes_the_editing_frame_visible() {
        TaggedValue value = stringMap.newValue();
        value.setTags(PageTags.of("use_for_title"));

        testObject.edit(value);

        assertTrue(testObject.frame.isVisible());
    }

    @Test
    public void edit_puts_the_edited_text_in_the_editor() {
        TaggedValue value = stringMap.newValue();
        value.setTags(PageTags.of("use_for_title"));
        value.setContents("contents");
        screen().show();

        testObject.edit(value);

        assertEquals("contents", testObject.editor.getText());
    }

    Screen screen() {
        PageLink link = PageLink.of("");
        Page page = new Page(link) {
            @Override
            public XComponent layoutForPortrait() {
                return null;
            }
        };
        return Screen.of(link, page);
    }
}