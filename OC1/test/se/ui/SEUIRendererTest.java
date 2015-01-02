package se.ui;


import common.ui.UIButton;
import common.ui.UIColumnLayout;
import common.ui.UIComponent;
import common.ui.UILabel;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SEUIRendererTest {

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new UILabel(""),JLabel.class);
        assertRendersAs(new UIButton(), JButton.class);
        assertRendersAs(new UIColumnLayout(),JPanel.class);
    }

    private void assertRendersAs(UIComponent component, Class c) {
        assertTrue(c.isInstance(render(component)));
    }

    @Test
    public void render_throws_IllegalArgumentException_for_unsupported_component_null() {
        try {
            render(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("null",e.getMessage());
        }
    }

    @Test
    public void render_throws_IllegalArgumentException_for_unsupported_component_string() {
        UIComponent component = new UIComponent();
        try {
            render(component);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(component.getClass().getName(),e.getMessage());
        }
    }

    private JComponent render(UIComponent component) {
        return SEUIRenderer.render(component);
    }
}