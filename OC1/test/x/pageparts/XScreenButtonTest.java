package x.pageparts;

import config.ShouldRun;
import fake.FakePage;
import fake.FakeXRegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.page.PageFactory;
import x.page.PageLink;
import x.ui.IForm;
import x.ui.IFormFactory;

import static mach.Mocks.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class XScreenButtonTest {

    IForm form;
    IFormFactory formFactory;
    PageFactory pageFactory;
    String linkTarget = "test";
    PageLink link = PageLink.of(linkTarget);
    FakePage page;
    FakePage[] pages;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        Mocks.init(this);
        FakeXRegistryLoader.load();
        Registry.put(IFormFactory.class, formFactory);
        Registry.put(PageFactory.class, pageFactory);
        _(form);    formFactory.newForm(link);
        _();        form.show();

        page = new FakePage(link);
        pages = new FakePage[] {page};

        _();             form.layout(page.xComponent);
        _(); wild(null); form.setBackCommand(null);
        _(pages);      pageFactory.create(link);
    }

    @Test
    public void builder_throws_exception_when_no_screen_specified() {
        try {
            XScreenButton.builder().build();
            fail();
        } catch (RuntimeException e) {
            assertEquals("No page specified",e.getMessage());
        }
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_reference() {
        XScreenButton.builder().leadingTo(page).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_link() {
        XScreenButton.builder().leadingTo(link).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void tapping_screen_button_shows_screen_specified_by_link_target() {
        XScreenButton.builder().leadingTo(linkTarget).build().onTap();

        verifyScreenShown();
    }

    @Test
    public void button_is_built_with_specified_text() {
        String expected = "button text";
        String actual = XScreenButton.builder().text(expected).leadingTo(page).build().getText();

        assertEquals(expected, actual);
    }

    @Test
    public void button_is_built_with_specified_image() {
        String expected = "image name";
        String actual = XScreenButton.builder().image(expected).leadingTo(page).build().icon;

        assertEquals(expected, actual);
    }

    void verifyScreenShown() {
        verify();
        form.layout(page.xComponent);
        form.show();
        assertTrue(page.layoutForPortrait);
    }
}