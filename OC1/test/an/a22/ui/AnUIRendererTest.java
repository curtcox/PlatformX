package an.a22.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.Registry;
import x.uiwidget.*;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnUIRendererTest {

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
        FakeAnRegistryLoader.load();
    }

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new UILabel(""),TextView.class);
        assertRendersAs(new FakeButton(""), Button.class);
        assertRendersAs(new UIColumn(),LinearLayout.class);
        assertRendersAs(new UIRow(),LinearLayout.class);
        assertRendersAs(new UIFlow(),View.class);
        assertRendersAs(new UIPeeredComponent(new TextView(context())),TextView.class);
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
        View actual = render(flow);
        assertTrue(actual instanceof View);
    }

    @Test
    public void render_empty_column_produces_proper_layout() {
        UIColumn column = new UIColumn();
        LinearLayout actual = (LinearLayout) render(column);
        assertEquals(LinearLayout.VERTICAL,actual.getOrientation());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        UIRow row = new UIRow();
        LinearLayout actual = (LinearLayout) render(row);
        assertEquals(LinearLayout.HORIZONTAL,actual.getOrientation());
    }

    @Test
    public void render_flow_with_a_label() {
        String text = toString();
        UIFlow flow = new UIFlow(new UILabel(text));
        LinearLayout actual = (LinearLayout) render(flow);
        assertEquals(1,actual.getChildCount());
        TextView label = (TextView) actual.getChildAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        UIColumn column = new UIColumn(new UILabel(text));
        LinearLayout actual = (LinearLayout) render(column);
        assertEquals(1,actual.getChildCount());
        TextView label = (TextView) actual.getChildAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_row_with_a_label() {
        String text = toString();
        UIRow row = new UIRow(new UILabel(text));
        LinearLayout actual = (LinearLayout) render(row);
        assertEquals(1,actual.getChildCount());
        TextView label = (TextView) actual.getChildAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_a_label() {
        String text = toString();
        TextView actual = (TextView) render(new UILabel(text));
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
        button.performClick();
        assertTrue(fakeButton.tapped);
    }

    @Test
    public void render_column_with_a_button_and_a_label() {
        UIColumn column = new UIColumn(new FakeButton(""),new UILabel());
        LinearLayout actual = (LinearLayout) render(column);
        assertEquals(2,actual.getChildCount());
        assertTrue(actual.getChildAt(0) instanceof Button);
        assertTrue(actual.getChildAt(1) instanceof TextView);
    }

    private View render(UIComponent component) {
        return AnUIRenderer.render(component);
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

}