package an.a22.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import config.ShouldRun;
import fake.FakeAnRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.app.Registry;
import x.uiwidget.*;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnUIRendererTest {

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
        assumeTrue(ShouldRun.Android);
        FakeAnRegistryLoader.load();
    }

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new XLabel(""),TextView.class);
        assertRendersAs(new FakeButton(""), Button.class);
        assertRendersAs(new XColumn(),LinearLayout.class);
        assertRendersAs(new XRow(),LinearLayout.class);
        assertRendersAs(new XFlow(),View.class);
        assertRendersAs(new XPeeredComponent(new TextView(context())),TextView.class);
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
        View actual = render(flow);
        assertTrue(actual instanceof View);
    }

    @Test
    public void render_empty_column_produces_proper_layout() {
        XColumn column = new XColumn();
        LinearLayout actual = (LinearLayout) render(column);
        assertEquals(LinearLayout.VERTICAL,actual.getOrientation());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        XRow row = new XRow();
        LinearLayout actual = (LinearLayout) render(row);
        assertEquals(LinearLayout.HORIZONTAL,actual.getOrientation());
    }

    @Test
    public void render_flow_with_a_label() {
        String text = toString();
        XFlow flow = new XFlow(new XLabel(text));
        LinearLayout actual = (LinearLayout) render(flow);
        assertEquals(1,actual.getChildCount());
        TextView label = (TextView) actual.getChildAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_column_with_a_label() {
        String text = toString();
        XColumn column = new XColumn(new XLabel(text));
        LinearLayout actual = (LinearLayout) render(column);
        assertEquals(1,actual.getChildCount());
        TextView label = (TextView) actual.getChildAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_row_with_a_label() {
        String text = toString();
        XRow row = new XRow(new XLabel(text));
        LinearLayout actual = (LinearLayout) render(row);
        assertEquals(1,actual.getChildCount());
        TextView label = (TextView) actual.getChildAt(0);
        assertEquals(text,label.getText());
    }

    @Test
    public void render_a_label() {
        String text = toString();
        TextView actual = (TextView) render(new XLabel(text));
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
        button.performClick();
        assertTrue(fakeButton.tapped);
    }

    @Test
    public void render_column_with_a_button_and_a_label() {
        XColumn column = new XColumn(new FakeButton(""),new XLabel());
        LinearLayout actual = (LinearLayout) render(column);
        assertEquals(2,actual.getChildCount());
        assertTrue(actual.getChildAt(0) instanceof Button);
        assertTrue(actual.getChildAt(1) instanceof TextView);
    }

    private View render(XComponent component) {
        return AnUIRenderer.render(component);
    }

    private static Context context() {
        return Registry.get(Context.class);
    }

}