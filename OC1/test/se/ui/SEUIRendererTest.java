package se.ui;

import x.uiwidget.*;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SEUIRendererTest {

    static class FakeButton extends XButton {

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
        assertRendersAs(new XLabel(""),JLabel.class);
        assertRendersAs(new FakeButton(""), JButton.class);
        assertRendersAs(new XColumn(),JPanel.class);
        assertRendersAs(new XRow(),JPanel.class);
        assertRendersAs(new XFlow(),JPanel.class);
        assertRendersAs(new XPeeredComponent(new JLabel()), JLabel.class);
        assertRendersAs(new XPeeredComponent(new JButton()), JButton.class);
    }

    private void assertRendersAs(XComponent component, Class c) {
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
        XComponent component = new XComponent();
        try {
            render(component);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(component.getClass().getName(),e.getMessage());
        }
    }

    @Test
    public void render_empty_flow_produces_proper_layout() {
        XFlow flow = new XFlow();
        JPanel actual = (JPanel) render(flow);
        LayoutManager layout = actual.getLayout();
        assertTrue(layout instanceof FlowLayout);
    }

    @Test
    public void render_empty_column_produces_proper_layout() {
        XColumn column = new XColumn();
        JPanel actual = (JPanel) render(column);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.Y_AXIS,layout.getAxis());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        XRow row = new XRow();
        JPanel actual = (JPanel) render(row);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.X_AXIS,layout.getAxis());
    }

    @Test
    public void render_flow_with_a_label() {
        String text = toString();
        XFlow flow = new XFlow(new XLabel(text));
        JPanel actual = (JPanel) render(flow);
        assertEquals(1,actual.getComponentCount());
        JLabel label = (JLabel) actual.getComponent(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        XColumn column = new XColumn(new XLabel(text));
        JPanel actual = (JPanel) render(column);
        assertEquals(1,actual.getComponentCount());
        JLabel label = (JLabel) actual.getComponent(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_row_with_a_label() {
        String text = toString();
        XRow row = new XRow(new XLabel(text));
        JPanel actual = (JPanel) render(row);
        assertEquals(1,actual.getComponentCount());
        JLabel label = (JLabel) actual.getComponent(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_a_label() {
        String text = toString();
        JLabel actual = (JLabel) render(new XLabel(text));
        assertEquals(text,actual.getText());
    }

    @Test
    public void render_a_button_produces_button_with_proper_text() {
        String text = toString();
        XButton button = new FakeButton("");
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
        XColumn column = new XColumn(new FakeButton(""),new XLabel());
        JPanel actual = (JPanel) render(column);
        assertEquals(2,actual.getComponentCount());
        assertTrue(actual.getComponent(0) instanceof JButton);
        assertTrue(actual.getComponent(1) instanceof JLabel);
    }

    private JComponent render(XComponent component) {
        return SEUIRenderer.render(component);
    }
}