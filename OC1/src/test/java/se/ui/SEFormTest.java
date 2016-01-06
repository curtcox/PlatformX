package se.ui;

import config.ShouldRun;
import x.app.Registry;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;
import fake.FakeSERegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.util.TaggedValue;
import se.util.TaggedValueStringMap;

import javax.swing.*;

import java.awt.*;

import static mach.Mocks.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEFormTest {

    String title = random("link");
    EditCommand editCommand = new EditCommand();
    Events.Listener listener;
    PageLink link = PageLink.of(title);
    TaggedValue taggedValue;
    TaggedValueStringMap stringMap;
    SEForm testObject = new SEForm(link,editCommand);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE_UI);
        Mocks.init(this);
        _(); wild(null); listener.onEvent(null);
    }

    @Test
    public void can_create() {
        assertNotNull(new SEForm(PageLink.of("")));
    }

    @Test
    public void is_IForm() {
        assertTrue(new SEForm(PageLink.of("")) instanceof IForm);
    }

    @Test
    public void title_is_set_from_constructor() {
        SEForm testObject = new SEForm(link);

        assertEquals(title, testObject.getTitle());
    }

    @Test
    public void link_is_set_from_constructor() {
        SEForm testObject = new SEForm(link);

        assertSame(link, testObject.getScreenLink());
    }

    @Test
    public void uses_border_container() {
        testObject.layout(new XLabel("!!"));
        Component container = testObject.getComponents()[0];
        assertTrue(container instanceof SEBorderContainer);
    }

    @Test
    public void there_are_no_components_when_none_have_been_added() {
        assertEquals(0,testObject.getComponents().length);
    }

    @Test
    public void layout_produces_one_component_plus_navigation_panel_for_a_label() {
        testObject.layout(new XLabel("!!"));
        SEBorderContainer container = (SEBorderContainer) testObject.getComponents()[0];
        assertEquals(2, container.getComponents().length);
    }

    @Test
    public void layout_is_idempotent() {
        XComponent layout = new XLabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(1, testObject.getComponents().length);
        }
    }

    @Test
    public void layout_produces_a_matching_label_for_a_label() {
        String text = random("text");
        testObject.layout(new XLabel(text));
        SEBorderContainer container = (SEBorderContainer) testObject.getComponents()[0];
        JLabel label = (JLabel) container.getComponents()[0];
        assertSame(text, label.getText());
    }

    @Test
    public void editButtonClicked_triggers_edit_command_with_tagged_value_when_there_is_one_tagged_value_found() {
        FakeSERegistryLoader.load();
        XComponent layout = new XLabel(random("label"));
        Events events = new Events();
        Registry.put(Events.class,events);
        Registry.put(TaggedValueStringMap.class,stringMap);
        events.registerListenerFor(listener,EditTaggedValueEvent.class);
        _(new TaggedValue[] {taggedValue}); stringMap.getValuesFor(link.tags);

        testObject.layout(layout);

        testObject.editButtonClicked();

        verify();
        wild(null); listener.onEvent(null); EditTaggedValueEvent value = arg();
        assertSame(taggedValue, value.taggedValue);
    }

    @Test
    public void editButtonClicked_triggers_edit_command_with_title_and_layout_when_there_is_more_than_one_tagged_value_found() {
        FakeSERegistryLoader.load();
        XComponent layout = new XLabel(random("label"));
        Events events = new Events();
        Registry.put(Events.class,events);
        Registry.put(TaggedValueStringMap.class,stringMap);
        events.registerListenerFor(listener,EditLinkEvent.class);
        _(new TaggedValue[2]); stringMap.getValuesFor(link.tags);

        testObject.layout(layout);

        testObject.editButtonClicked();

        verify();
        wild(null); listener.onEvent(null); EditLinkEvent value = arg();
        assertEquals(title,value.link.title());
        assertEquals(layout,value.layout);
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}