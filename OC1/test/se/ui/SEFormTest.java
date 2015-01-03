package se.ui;

import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class SEFormTest {

    SEForm testObject = new SEForm("");

    @Test
    public void can_create() {
        assertNotNull(new SEForm(""));
    }

    @Test
    public void title_is_set_from_constructor() {
        String title = toString();
        SEForm testObject = new SEForm(title);

        assertSame(title, testObject.getTitle());
    }

    @Test
    public void uses_border_layout() {
        LayoutManager layout = testObject.getLayout();
        assertTrue(layout instanceof BorderLayout);
    }

    @Test
    public void there_are_no_components_when_none_have_been_added() {
        assertEquals(0,testObject.getComponents().length);
    }

    @Test
    public void layout_produces_one_component_plus_navigation_panel_for_a_label() {
        testObject.layout(new UILabel("!!"));
        assertEquals(2, testObject.getComponents().length);
    }

    @Test
    public void layout_is_idempotent() {
        UIComponent layout = new UILabel("!!");

        for (int i=0; i<3; i++) {
            testObject.layout(layout);
            assertEquals(2, testObject.getComponents().length);
        }
    }

    @Test
    public void layout_produces_a_matching_label_for_a_label() {
        String text = random();
        testObject.layout(new UILabel(text));
        JLabel label = (JLabel) testObject.getComponents()[0];
        assertSame(text, label.getText());
    }


    private String random() {
        return toString();
    }

}