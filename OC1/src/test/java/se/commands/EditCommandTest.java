package se.commands;

import config.ShouldRun;
import fake.FakePage;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.ui.EditLinkEvent;
import se.events.EditTaggedValueEvent;
import se.util.MutableTaggedValue;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.page.Page;
import x.page.PageLink;
import x.screen.Screen;
import x.ui.IForm;
import x.ui.IFormFactory;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;

import static mach.Mocks.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class EditCommandTest {

    XComponent layout = new XLabel(random("label"));
    String title = random("link");
    PageLink link = PageLink.of(title);
    Events.Listener listener;
    Events events = new Events();
    MutableTaggedValue taggedValue;
    TaggedValueStringMap taggedValues;
    Page page = new FakePage(link);
    EditCommand testObject = EditCommand.of();
    IFormFactory formFactory;
    IForm form;
    ILogManager logManager;
    ILog log;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
        Mocks.init(this);
        _(); wild(null); listener.onEvent(null);
        _(form); formFactory.newForm(link);
        _(log); wild(null); logManager.getLog(null);
        Registry.put(ILogManager.class,logManager);
        Registry.put(IFormFactory.class,formFactory);
        Registry.put(TaggedValueStringMap.class,taggedValues);
        Registry.put(Events.class, events);
    }

    @Test
    public void can_create() {
        assertNotNull(EditCommand.of());
    }

    @Test
    public void command_is_edit() {
        assertEquals("Edit",testObject.command);
    }

    @Test
    public void action_posts_EditLinkEvent_with_title_and_layout_from_action_for_event_bus_value_when_multiple_tagged_values_are_found() {
        _(new TaggedValue[2]); taggedValues.getValuesFor(link.tags);
        events.registerListenerFor(listener, EditLinkEvent.class);
        testObject.action(link,layout);

        verify();
        wild(null); listener.onEvent(null);  EditLinkEvent event = arg();
        assertSame(title, event.link.title());
        assertSame(layout, event.layout);
    }

    @Test
    public void action_posts_EditTaggedValue_page_there_is_one_matching_source() {
        _(new TaggedValue[] {taggedValue}); taggedValues.getValuesFor(link.tags);
        events.registerListenerFor(listener, EditTaggedValueEvent.class);
        Screen.of(page).show();

        testObject.action(link,layout);

        verify();
        wild(null); listener.onEvent(null);  EditTaggedValueEvent event = arg();
        assertSame(page, event.page);
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

    @Test
    public void action_posts_EditTaggedValueEvent_when_there_is_no_matching_source() {
        _(new TaggedValue[0]); taggedValues.getValuesFor(link.tags);
        _(taggedValue);        taggedValues.newValue(link.tags);
        events.registerListenerFor(listener, EditTaggedValueEvent.class);
        Screen.of(page).show();

        testObject.action(link,layout);

        verify();
        wild(null); listener.onEvent(null);  EditTaggedValueEvent event = arg();
        assertSame(taggedValue, event.taggedValue);
        taggedValues.newValue(link.tags);
    }

    private String random(String prefix) {
        return (prefix + toString()).toLowerCase();
    }
}