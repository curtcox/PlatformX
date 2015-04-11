package common.screenparts;

import common.screen.ScreenLink;
import fake.FakeCommonRegistryLoader;
import fake.FakeScreen;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    public void tapping_screen_button_shows_screen() {
        ScreenButton.builder().leadingTo(screen).build().onTap();

        assertTrue(screen.layoutForPortrait);
    }

}