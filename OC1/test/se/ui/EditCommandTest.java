package se.ui;

import common.screen.ScreenLink;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import org.junit.Test;

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
    public void uses_title_and_layout_from_action() {
        UIComponent layout = new UILabel(random("label"));
        String title = random("link");
        ScreenLink link = ScreenLink.of(title);
        testObject.action(link,layout);
        assertSame(title, testObject.link.tags.toString());
        assertSame(layout, testObject.layout);
    }

    private String random(String prefix) {
        return (prefix + toString()).toLowerCase();
    }
}