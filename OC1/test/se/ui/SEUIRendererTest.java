package se.ui;


import common.ui.UIButton;
import common.ui.UIColumnLayout;
import common.ui.UIComponent;
import common.ui.UILabel;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

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

    @Test
    public void render_empty_column_produces_proper_layout() {
        UIColumnLayout column = new UIColumnLayout();
        JPanel actual = (JPanel) render(column);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.Y_AXIS,layout.getAxis());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        UIColumnLayout column = new UIColumnLayout(new UILabel(text));
        JPanel actual = (JPanel) render(column);
        assertEquals(1,actual.getComponentCount());
        JLabel label = (JLabel) actual.getComponent(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_a_label() {
        String text = toString();
        JLabel actual = (JLabel) render(new UILabel(text));
        assertEquals(text,actual.getText());
    }

    @Test
    public void render_a_button() {
        String text = toString();
        UIButton button = new UIButton();
        button.text = text;
        JButton actual = (JButton) render(button);

        assertEquals(text,actual.getText());
    }

    @Test
    public void render_column_with_a_button_and_a_label() {
        UIColumnLayout column = new UIColumnLayout(new UIButton(),new UILabel());
        JPanel actual = (JPanel) render(column);
        assertEquals(2,actual.getComponentCount());
        assertTrue(actual.getComponent(0) instanceof JButton);
        assertTrue(actual.getComponent(1) instanceof JLabel);
    }

    private JComponent render(UIComponent component) {
        return SEUIRenderer.render(component);
    }
}