package se.ui;

import config.ShouldRun;
import org.junit.Before;
import x.page.PageLink;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEDisplayTest {

    SEForm form = new SEForm(PageLink.of(""));
    SEDisplay testObject = SEDisplay.of();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
    }

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