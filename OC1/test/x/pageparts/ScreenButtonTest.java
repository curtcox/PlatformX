package x.pageparts;

import x.Registry;
import x.page.PageFactory;
import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;
import fake.FakeCommonRegistryLoader;
import fake.FakePage;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks.*;
import static org.junit.Assert.*;

public class ScreenButtonTest  {

    IForm form;
    IFormFactory formFactory;
    PageFactory pageFactory;
    String linkTarget = "test";
    PageLink link = PageLink.of(linkTarget);
    FakePage page;
    FakePage[] pages;

    @Before
    public void setUp() {
        Mocks.init(this);
        FakeCommonRegistryLoader.load();
        Registry.put(IFormFactory.class, formFactory);
        Registry.put(PageFactory.class, pageFactory);
        _(form);    formFactory.newForm(link);
        _();        form.show();

        page = new FakePage(link);
        pages = new FakePage[] {page};

        _();             form.layout(page.uiComponent);
        _(); wild(null); form.setBackCommand(null);
        _(pages);      pageFactory.create(link);
    }

    @Test
    public void builder_throws_exception_when_no_screen_specified() {
        try {
            ScreenButton.builder().build();
            fail();
        } catch (RuntimeException e) {
            assertEquals("No page specified",e.getMessage());
        }
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_reference() {
        ScreenButton.builder().leadingTo(page).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_link() {
        ScreenButton.builder().leadingTo(link).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_link_target() {
        ScreenButton.builder().leadingTo(linkTarget).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void button_is_built_with_specified_text() {
        String expected = "button text";
        String actual = ScreenButton.builder().text(expected).leadingTo(page).build().getText();

        assertEquals(expected, actual);
    }

    @Test
    public void button_is_built_with_specified_image() {
        String expected = "image name";
        String actual = ScreenButton.builder().image(expected).leadingTo(page).build().icon;

        assertEquals(expected, actual);
    }

    void verifyScreenShown() {
        verify();
        form.layout(page.uiComponent);
        form.show();
        assertTrue(page.layoutForPortrait);
    }
}