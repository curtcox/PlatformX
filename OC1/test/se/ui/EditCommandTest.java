package se.ui;

import se.events.Events;
import common.Registry;
import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import org.junit.Test;
import se.events.SimpleListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class EditCommandTest {

    EditCommand testObject = new EditCommand();

    @Test
    public void can_create() {
        assertNotNull(new EditCommand());
    }

    @Test
    public void command_is_edit() {
        assertEquals("Edit",testObject.command);
    }

    @Test
    public void uses_title_and_layout_from_action_for_event_bus_value() {
        UIComponent layout = new UILabel(random("label"));
        String title = random("link");
        ScreenLink link = ScreenLink.of(title);
        Events events = new Events();
        SimpleListener listener = new SimpleListener();
        Registry.put(Events.class,events);

        testObject.action(link,layout);

        EditCommand.Value value = (EditCommand.Value) listener.getLast();
        assertSame(title, value.link.title());
        assertSame(layout, value.layout);
    }

    private String random(String prefix) {
        return (prefix + toString()).toLowerCase();
    }
}