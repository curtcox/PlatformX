package common.screenparts;

import common.Registry;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.ui.IForm;
import common.ui.IFormFactory;
import fake.FakeCommonRegistryLoader;
import fake.FakeScreen;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks.*;
import static org.junit.Assert.*;

public class ScreenButtonTest  {

    IForm form;
    IFormFactory formFactory;
    ScreenFactory screenFactory;
    ScreenLink link = ScreenLink.of("test");
    FakeScreen screen;
    FakeScreen[] screens;

    @Before
    public void setUp() {
        Mocks.init(this);
        FakeCommonRegistryLoader.load();
        Registry.put(IFormFactory.class, formFactory);
        Registry.put(ScreenFactory.class, screenFactory);
        _(form);    formFactory.newForm(link);
        _();        form.show();

        screen = new FakeScreen(link);
        screens = new FakeScreen[] {screen};

        _();             form.layout(screen.uiComponent);
        _(); wild(null); form.setBackCommand(null);
        _(screens); screenFactory.create(link);
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

        verifyScreenShown();
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_link() {
        ScreenButton.builder().leadingTo(link).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void button_is_built_with_specified_text() {
        String expected = "button text";
        String actual = ScreenButton.builder().text(expected).leadingTo(screen).build().getText();

        assertEquals(expected, actual);
    }

    void verifyScreenShown() {
        verify();
        form.layout(screen.uiComponent);
        form.show();
        assertTrue(screen.layoutForPortrait);
    }
}