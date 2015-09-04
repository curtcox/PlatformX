package x.screen;

import config.ShouldRun;
import fake.FakeForm;
import fake.FakeXRegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.ui.IFormFactory;
import x.uiwidget.XComponent;
import x.uiwidget.XContainer;

import static mach.Mocks.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * I'm ready for my closeup.
 */
public class XScreenTest {

    FakeForm form = new FakeForm();
    String name = random("link");
    XComponent layout = new XComponent();
    PageLink link = PageLink.of(name);
    PageLink link1 = PageLink.of("first");
    PageLink link2 = PageLink.of("second");
    PageFactory pageFactory;
    IFormFactory formFactory;
    ILog log;
    ILogManager logManager;
    ExamplePage page;
    Screen screen;

    class ExamplePage extends Page {

        public ExamplePage(PageLink link) {
            super(link);
        }

        @Override
        public XComponent layoutForPortrait() {
            return layout;
        }

        @Override
        public String toString() {
            return link.toString();
        }
    }

    @Before
    public void setup() {
        assumeTrue(ShouldRun.X);
        FakeXRegistryLoader.load();
        page = new ExamplePage(link);
        Mocks.init(this);
        Registry.put(ILogManager.class, logManager);
        Registry.put(PageFactory.class, pageFactory);
        Registry.put(IFormFactory.class,formFactory);
        _(form); formFactory.newForm(link);
        _(log); logManager.getLog(Screen.class);
        screen = Screen.of(page);
    }

    @Test
    public void can_create() {
        assertNotNull(new ExamplePage(link));
    }

    @Test
    public void uses_screenLink_from_constructor() {
        assertSame(link, page.link);
    }

    @Test
    public void show_sets_form_layout() {
        screen.show();

        assertSame(layout, form.layout);
    }

    @Test
    public void show_logs_page_link() {
        screen.show();

        verify();
        log.log("show " + link.toString());
    }

    @Test
    public void refresh_sets_form_layout() {
        screen.refresh();

        assertSame(layout, form.layout);
    }

    @Test
    public void show_sets_showing_screen() {
        screen.show();

        assertSame(screen,Screen.getShowing());
    }

    @Test
    public void show_calls_show_on_form() {
        screen.show();

        assertTrue(form.showWasCalled);
    }

    @Test
    public void getShowing_returns_original_screen_after_going_back_to_it() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        _(form1); formFactory.newForm(link1);
        _(form2); formFactory.newForm(link2);
        Screen first = Screen.of(new ExamplePage(link1));
        Screen second = Screen.of(new ExamplePage(link2));

        first.show();
        second.show();

        second.back();

        assertSame(first,Screen.getShowing());
    }

    @Test
    public void back_shows_previously_shown_screen() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        _(form1); formFactory.newForm(link1);
        _(form2); formFactory.newForm(link2);
        Screen first = Screen.of(new ExamplePage(link1));
        Screen second = Screen.of(new ExamplePage(link2));

        first.show();
        second.show();

        second.back();

        assertTrue(form1.showBackWasCalled);
    }

    @Test
    public void setBackCommand_is_called_when_there_is_a_previous_screen() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        _(form1); formFactory.newForm(link1);
        _(form2); formFactory.newForm(link2);
        Screen first = Screen.of(new ExamplePage(link1));
        Screen second = Screen.of(new ExamplePage(link2));

        first.show();
        second.show();

        second.back();

        assertEquals("Back", form2.getBackCommand().command);
    }

    private String random(String name) {
        return name + toString();
    }

    @Test
    public void show_makes_page_the_one_showing_when_factory_returns_one_link_for_it() {
        _(new Page[] {page}); pageFactory.create(link);

        Screen.show(link);

        assertSame(page, Screen.getShowing().page);
    }

    static class ScreenThatThrowsExceptionOnLayout extends Page {
        RuntimeException e;
        ScreenThatThrowsExceptionOnLayout(PageLink link, RuntimeException e) {
            super(link);
            this.e = e;
        }
        @Override public XContainer layoutForPortrait() {
            throw e;
        }
    }

    @Test
    public void show_logs_exception_if_one_is_thrown_by_screen() {
        RuntimeException e = new RuntimeException();
        _(); log.log(e);
        Screen screen = Screen.of(new ScreenThatThrowsExceptionOnLayout(link,e));

        screen.show();

        verify();
        log.log(e);
    }

    @Test
    public void show_logs_an_exception_when_factory_returns_no_pages_for_link() {
        _(); wild(null); log.log((Throwable) null);

        _(new Page[0]); pageFactory.create(link);

        RuntimeException thrown = null;
        try {
            Screen.show(link);
            fail();
        } catch (RuntimeException e) {
            thrown = e;
        }

        verify();
        log.log(thrown);
    }

    @Test
    public void show_logs_an_exception_when_factory_returns_multiple_pages_for_link() {
        _(); wild(null); log.log((Throwable) null);

        _(new Page[2]); pageFactory.create(link);

        RuntimeException thrown = null;
        try {
            Screen.show(link);
            fail();
        } catch (RuntimeException e) {
            thrown = e;
        }

        verify();
        log.log(thrown);
    }

    @Test
    public void show_throws_an_exception_when_factory_returns_no_pages_for_link() {
        _(new Page[0]); pageFactory.create(link);

        try {
            Screen.show(link);
            fail();
        } catch (RuntimeException e) {
            assertEquals("No pages found for " + link,e.getMessage());
        }
    }

    @Test
    public void show_shows_single_page_when_factory_returns_1_page_for_link() {
        _(new Page[] {page}); pageFactory.create(link);

        Screen.show(link);

        FakeForm form = (FakeForm) Screen.getShowing().form;
        assertSame(layout,form.layout);
    }

    @Test
    public void show_throws_and_exception_when_factory_returns_multiple_pages_for_link() {
        _(new Page[2]); pageFactory.create(link);

        try {
            Screen.show(link);
            fail();
        } catch (RuntimeException e) {
            assertEquals("Multiple pages (2) found for " + link,e.getMessage());
        }
    }

}