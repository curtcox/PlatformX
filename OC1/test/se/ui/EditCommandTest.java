package se.ui;

import mach.Mocks;
import org.junit.Before;
import se.events.Events;
import common.Registry;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import org.junit.Test;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

import static mach.Mocks.*;
import static org.junit.Assert.*;

public class EditCommandTest {

    UIComponent layout = new UILabel(random("label"));
    String title = random("link");
    ScreenLink link = ScreenLink.of(title);
    Events.Listener listener;
    Events events = new Events();
    TaggedValue taggedValue;
    TaggedValueStringMap taggedValues;
    EditCommand testObject = new EditCommand();

    @Before
    public void setUp() {
        Mocks.init(this);
        _(); wild(null); listener.onEvent(null);
        Registry.put(TaggedValueStringMap.class,taggedValues);
        Registry.put(Events.class, events);
    }

    @Test
    public void can_create() {
        assertNotNull(new EditCommand());
    }

    @Test
    public void command_is_edit() {
        assertEquals("Edit",testObject.command);
    }

    @Test
    public void action_posts_EditLinkEvent_with_title_and_layout_from_action_for_event_bus_value_when_no_tagged_value_is_found() {
        _(new TaggedValue[0]); taggedValues.getValuesFor(link.tags);
        events.registerListenerFor(listener, EditLinkEvent.class);
        testObject.action(link,layout);

        verify();
        wild(null); listener.onEvent(null);  EditLinkEvent event = arg();
        assertSame(title, event.link.title());
        assertSame(layout, event.layout);
    }

    @Test
    public void action_posts_EditTaggedValueEvent_when_there_is_one_matching_source() {
        _(new TaggedValue[] {taggedValue}); taggedValues.getValuesFor(link.tags);
        events.registerListenerFor(listener, EditTaggedValueEvent.class);

        testObject.action(link,layout);

        verify();
        wild(null); listener.onEvent(null);  EditTaggedValueEvent event = arg();
        assertSame(taggedValue, event.taggedValue);
    }

    private String random(String prefix) {
        return (prefix + toString()).toLowerCase();
    }
}