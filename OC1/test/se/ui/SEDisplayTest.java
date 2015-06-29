package se.ui;

import x.page.PageLink;
import org.junit.Test;

import static org.junit.Assert.*;

public class SEDisplayTest {

    SEForm form = new SEForm(PageLink.of(""));
    SEDisplay testObject = SEDisplay.of();

    @Test
    public void display_is_not_null() {
        assertNotNull(SEDisplay.of());
    }

    @Test
    public void display_is_a_singleton() {
        assertSame(SEDisplay.of(),SEDisplay.of());
    }

    @Test
    public void show_sets_current_form() {
        testObject.show(form);

        assertSame(form, testObject.getCurrent());
    }
}