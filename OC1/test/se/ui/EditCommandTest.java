package se.ui;

import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import junit.framework.TestCase;
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
        String title = random("title");
        testObject.action(title,layout);
        assertSame(title, testObject.title);
        assertSame(layout, testObject.layout);
    }

    private String random(String prefix) {
        return prefix + toString();
    }
}