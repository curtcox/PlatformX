package c1.ui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import common.uiwidget.*;
import fake.FakeSERegistryLoader;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class C1UIRendererTest {

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
        FakeUIManager.of();
        FakeSERegistryLoader.load();
    }

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new UILabel(""),Label.class);
        assertRendersAs(new FakeButton(""), Button.class);
        assertRendersAs(new UIColumn(),Container.class);
        assertRendersAs(new UIRow(),Container.class);
        assertRendersAs(new UIFlow(),Container.class);
        assertRendersAs(new UIPeeredComponent(new Label()),Label.class);
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
    public void render_empty_flow_produces_proper_layout() {
        UIFlow flow = new UIFlow();
        Container actual = (Container) render(flow);
        Layout layout = actual.getLayout();
        assertTrue(layout instanceof FlowLayout);
    }

    @Test
    public void render_empty_column_produces_proper_layout() {
        UIColumn column = new UIColumn();
        Container actual = (Container) render(column);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.Y_AXIS,layout.getAxis());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        UIRow row = new UIRow();
        Container actual = (Container) render(row);
        BoxLayout layout = (BoxLayout) actual.getLayout();
        assertEquals(BoxLayout.X_AXIS,layout.getAxis());
    }

    @Test
    public void render_flow_with_a_label() {
        String text = toString();
        UIFlow flow = new UIFlow(new UILabel(text));
        Container actual = (Container) render(flow);
        assertEquals(1,actual.getComponentCount());
        Label label = (Label) actual.getComponentAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        UIColumn column = new UIColumn(new UILabel(text));
        Container actual = (Container) render(column);
        assertEquals(1,actual.getComponentCount());
        Label label = (Label) actual.getComponentAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_row_with_a_label() {
        String text = toString();
        UIRow row = new UIRow(new UILabel(text));
        Container actual = (Container) render(row);
        assertEquals(1,actual.getComponentCount());
        Label label = (Label) actual.getComponentAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_a_label() {
        String text = toString();
        Label actual = (Label) render(new UILabel(text));
        assertEquals(text,actual.getText());
    }

    @Test
    public void render_a_button_produces_button_with_proper_text() {
        String text = toString();
        UIButton button = new FakeButton("");
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
        UIColumn column = new UIColumn(new FakeButton(""),new UILabel());
        Container actual = (Container) render(column);
        assertEquals(2,actual.getComponentCount());
        assertTrue(actual.getComponentAt(0) instanceof Button);
        assertTrue(actual.getComponentAt(1) instanceof Label);
    }

    private Component render(UIComponent component) {
        return C1UIRenderer.render(component);
    }
}