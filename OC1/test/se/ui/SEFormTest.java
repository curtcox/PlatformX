package se.ui;

import common.Registry;
import common.screen.ScreenLink;
import common.ui.IForm;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import fake.FakeSERegistryLoader;
import org.junit.Test;
import se.events.Events;
import se.events.SimpleListener;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class SEFormTest {

    String title = random("link");
    EditCommand editCommand = new EditCommand();
    ScreenLink link = ScreenLink.of(title);
    SEForm testObject = new SEForm(link,editCommand);

    @Test
    public void can_create() {
        assertNotNull(new SEForm(ScreenLink.of("")));
    }

    @Test
    public void is_IForm() {
        assertTrue(new SEForm(ScreenLink.of("")) instanceof IForm);
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
    public void uses_border_layout() {
        LayoutManager layout = testObject.getLayout();
        assertTrue(layout instanceof BorderLayout);
    }

    @Test
    public void there_are_no_components_when_none_have_been_added() {
        assertEquals(0,testObject.getComponents().length);
    }

    @Test
    public void layout_produces_one_component_plus_navigation_panel_for_a_label() {
        testObject.layout(new UILabel("!!"));
        assertEquals(2, testObject.getComponents().length);
    }

    @Test
    public void layout_is_idempotent() {
        UIComponent layout = new UILabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(2, testObject.getComponents().length);
        }
    }

    @Test
    public void layout_produces_a_matching_label_for_a_label() {
        String text = random("text");
        testObject.layout(new UILabel(text));
        JLabel label = (JLabel) testObject.getComponents()[0];
        assertSame(text, label.getText());
    }

    @Test
    public void editButtonClicked_triggers_edit_command_with_title_and_layout() {
        FakeSERegistryLoader.load();
        UIComponent layout = new UILabel(random("label"));
        Events events = new Events();
        SimpleListener listener = new SimpleListener();
        Registry.put(Events.class,events);
        testObject.layout(layout);

        testObject.editButtonClicked();

        EditCommand.Value value = (EditCommand.Value) listener.getLast();
        assertEquals(title,value.link.title());
        assertEquals(layout,value.layout);
    }

    private String random(String prefix) {
        return (prefix + toString().toLowerCase());
    }

}