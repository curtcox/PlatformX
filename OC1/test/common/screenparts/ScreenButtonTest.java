package common.screenparts;

import common.Registry;
import common.screen.ScreenLink;
import common.ui.IFormFactory;
import fake.FakeCommonRegistryLoader;
import fake.FakeFormFactory;
import fake.FakeScreen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScreenButtonTest  {

    ScreenLink link = ScreenLink.of("test");
    FakeScreen screen;

    @Before
    public void setUp() {
        FakeCommonRegistryLoader.load();
        screen = new FakeScreen(link);
    }

    @Test
    public void builder_throws_exception_when_no_screen_specified() {
        try {
            ScreenButton.builder().build();
            fail();
        } catch (RuntimeException e) {
            assertEquals("No screen specified",e.getMessage());
        }
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_reference() {
        ScreenButton.builder().leadingTo(screen).build().onTap();

        assertTrue(screen.layoutForPortrait);
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_link() {
        ScreenButton.builder().leadingTo(link).build().onTap();

        assertTrue(screen.layoutForPortrait);
    }

    @Test
    public void button_is_built_with_specified_text() {
        String expected = "button text";
        String actual = ScreenButton.builder().text(expected).leadingTo(screen).build().getText();

        assertEquals(expected, actual);
    }

}