package c1.ui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import x.uiwidget.*;
import fake.FakeSERegistryLoader;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class C1UIRendererTest {

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
        FakeUIManager.of();
        FakeSERegistryLoader.load();
    }

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new XLabel(""),Label.class);
        assertRendersAs(new FakeButton(""), Button.class);
        assertRendersAs(new XColumn(),Container.class);
        assertRendersAs(new XRow(),Container.class);
        assertRendersAs(new XFlow(),Container.class);
        assertRendersAs(new XPeeredComponent(new Label()),Label.class);
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
        Container actual = (Container) render(flow);
        Layout layout = actual.getLayout();
        assertTrue(layout instanceof FlowLayout);
    }

    @Test
    public void render_empty_column_produces_proper_layout() {
        XColumn column = new XColumn();
        Container actual = (Container) render(column);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.Y_AXIS,layout.getAxis());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        XRow row = new XRow();
        Container actual = (Container) render(row);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.X_AXIS,layout.getAxis());
    }

    @Test
    public void render_flow_with_a_label() {
        String text = toString();
        XFlow flow = new XFlow(new XLabel(text));
        Container actual = (Container) render(flow);
        assertEquals(1,actual.getComponentCount());
        Label label = (Label) actual.getComponentAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        XColumn column = new XColumn(new XLabel(text));
        Container actual = (Container) render(column);
        assertEquals(1,actual.getComponentCount());
        Label label = (Label) actual.getComponentAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_row_with_a_label() {
        String text = toString();
        XRow row = new XRow(new XLabel(text));
        Container actual = (Container) render(row);
        assertEquals(1,actual.getComponentCount());
        Label label = (Label) actual.getComponentAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_a_label() {
        String text = toString();
        Label actual = (Label) render(new XLabel(text));
        assertEquals(text,actual.getText());
    }

    @Test
    public void render_a_button_produces_button_with_proper_text() {
        String text = toString();
        XButton button = new FakeButton("");
        button.text = text;
        Button actual = (Button) render(button);

        assertEquals(text,actual.getText());
    }

    @Test
    public void render_an_action_button_produces_button_with_proper_action() throws InterruptedException {
        FakeButton fakeButton = new FakeButton("");
        Button button = (Button) render(fakeButton);
        button.released();

        Thread.sleep(50);
        assertTrue(fakeButton.tapped);
    }

    @Test
    public void render_column_with_a_button_and_a_label() {
        XColumn column = new XColumn(new FakeButton(""),new XLabel());
        Container actual = (Container) render(column);
        assertEquals(2,actual.getComponentCount());
        assertTrue(actual.getComponentAt(0) instanceof Button);
        assertTrue(actual.getComponentAt(1) instanceof Label);
    }

    private Component render(XComponent component) {
        return C1UIRenderer.render(component);
    }
}