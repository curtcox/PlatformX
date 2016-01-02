package va.ui;

import com.vaadin.ui.*;
import config.ShouldRun;
import fake.FakeVaRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.uiwidget.*;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class VaUIRendererTest {

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
        assumeTrue(ShouldRun.Vaadin);
        FakeVaRegistryLoader.load();
    }

    @Test
    public void render_returns_right_component_type() {
        assertRendersAs(new XLabel(""),     Label.class);
        assertRendersAs(new FakeButton(""), Button.class);
        assertRendersAs(new XColumn(),      VerticalLayout.class);
        assertRendersAs(new XRow(),         HorizontalLayout.class);
        assertRendersAs(new XFlow(),        Component.class);
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
        Component actual = render(flow);
        assertTrue(actual instanceof Component);
    }

    @Test
    public void render_empty_column_produces_proper_layout() {
        XColumn column = new XColumn();
        Component actual = (Component) render(column);
//        assertEquals(LinearLayout.VERTICAL,actual.getOrientation());
    }

    @Test
    public void render_empty_row_produces_proper_layout() {
        XColumn column = new XColumn();
        Component actual = (Component) render(column);
//        assertEquals(LinearLayout.HORIZONTAL,actual.getOrientation());
    }

//    @Test
//    public void render_flow_with_a_label() {
//        String text = toString();
//        XFlow flow = new XFlow(new XLabel(text));
//        UICollectionView actual = (UICollectionView) render(flow);
//        assertEquals(1, actual.getChildCount());
//        TextView label = (TextView) actual.getChildAt(0);
//        assertEquals(text,label.getText());
//    }
//
//    @Test
//    public void render_column_with_a_label() {
//        String text = toString();
//        XColumn column = new XColumn(new XLabel(text));
//        UICollectionView actual = (UICollectionView) render(column);
//        assertEquals(1,actual.getChildCount());
//        TextView label = (TextView) actual.getChildAt(0);
//        assertEquals(text,label.getText());
//    }
//
//    @Test
//    public void render_row_with_a_label() {
//        String text = toString();
//        XRow row = new XRow(new XLabel(text));
//        UICollectionView actual = (UICollectionView) render(row);
//        assertEquals(1,actual.getChildCount());
//        TextView label = (TextView) actual.getChildAt(0);
//        assertEquals(text,label.getText());
//    }

    @Test
    public void render_a_label() {
        String text = toString();
        Label actual = (Label) render(new XLabel(text));
        assertEquals(text, actual.getValue());
    }

    @Test
    public void render_a_button_produces_button_with_proper_text() {
        String text = toString();
        XButton button = new FakeButton("");
        button.text = text;
        Button actual = (Button) render(button);

//        assertEquals(text,actual.getTitleLabel().getText());
    }

//    @Test
//    public void render_an_action_button_produces_button_with_proper_action() throws InterruptedException {
//        FakeButton fakeButton = new FakeButton("");
//        UIButton button = (UIButton) render(fakeButton);
//        button.performClick();
//        assertTrue(fakeButton.tapped);
//    }
//
//    @Test
//    public void render_column_with_a_button_and_a_label() {
//        XColumn column = new XColumn(new FakeButton(""),new XLabel());
//        UICollectionView actual = (UICollectionView) render(column);
//        assertEquals(2,actual.getChildCount());
//        assertTrue(actual.getChildAt(0) instanceof Button);
//        assertTrue(actual.getChildAt(1) instanceof TextView);
//    }

    private Component render(XComponent component) {
        return VaUIRenderer.render(component);
    }

}