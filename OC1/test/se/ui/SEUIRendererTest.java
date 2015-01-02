package se.ui;

import common.ui.*;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SEUIRendererTest {

    static class FakeButton extends UIButton {

        boolean tapped;

        public FakeButton(String name) {
            super(name);
        }

        @Override
        public void onTap() {
            tapped = true;
        }
    }
    @Before
    public void setup() {
        FakeSERegistryLoader.load();
    }

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new UILabel(""),JLabel.class);
        assertRendersAs(new FakeButton(""), JButton.class);
        assertRendersAs(new UIColumn(),JPanel.class);
        assertRendersAs(new UIRow(),JPanel.class);
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
        UIColumn column = new UIColumn();
        JPanel actual = (JPanel) render(column);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.Y_AXIS,layout.getAxis());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        UIRow row = new UIRow();
        JPanel actual = (JPanel) render(row);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.X_AXIS,layout.getAxis());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        UIColumn column = new UIColumn(new UILabel(text));
        JPanel actual = (JPanel) render(column);
        assertEquals(1,actual.getComponentCount());
        JLabel label = (JLabel) actual.getComponent(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_row_with_a_label() {
        String text = toString();
        UIRow row = new UIRow(new UILabel(text));
        JPanel actual = (JPanel) render(row);
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
    public void render_a_button_produces_button_with_proper_text() {
        String text = toString();
        UIButton button = new FakeButton("");
        button.text = text;
        JButton actual = (JButton) render(button);

        assertEquals(text,actual.getText());
    }

    @Test
    public void render_an_action_button_produces_button_with_proper_action() {
        FakeButton fakeButton = new FakeButton("");
        JButton jButton = (JButton) render(fakeButton);
        jButton.doClick();

        assertTrue(fakeButton.tapped);
    }

    @Test
    public void render_column_with_a_button_and_a_label() {
        UIColumn column = new UIColumn(new FakeButton(""),new UILabel());
        JPanel actual = (JPanel) render(column);
        assertEquals(2,actual.getComponentCount());
        assertTrue(actual.getComponent(0) instanceof JButton);
        assertTrue(actual.getComponent(1) instanceof JLabel);
    }

    private JComponent render(UIComponent component) {
        return SEUIRenderer.render(component);
    }
}