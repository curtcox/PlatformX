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
    PageFactory factory;
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
        Registry.put(PageFactory.class,factory);
        Registry.put(IFormFactory.class,formFactory);
        _(form); formFactory.newForm(link);
        screen = Screen.of(link, page);
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
        Screen first = Screen.of(link1,new ExamplePage(link1));
        Screen second = Screen.of(link2,new ExamplePage(link2));

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
        Screen first = Screen.of(link1, new ExamplePage(link1));
        Screen second = Screen.of(link2,new ExamplePage(link2));

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
        Screen first = Screen.of(link1, new ExamplePage(link1));
        Screen second = Screen.of(link2, new ExamplePage(link2));

        first.show();
        second.show();

        second.back();

        assertEquals("Back", form2.getBackCommand().command);
    }

    private String random(String name) {
        return name + toString();
    }

    static class FakePage extends Page {
        FakePage() {
            super(PageLink.of("name"));
        }
        @Override public XContainer layoutForPortrait() { return null;}
    }

    @Test
    public void show_makes_page_the_one_showing_when_factory_returns_one_link_for_it() {
        PageLink link = PageLink.of("foo");
        Page page = new FakePage();
        Page[] screens = new Page[] { page };
        _(screens); factory.create(link);
        _(form); formFactory.newForm(link);

        Screen.show(link);

        assertSame(page, Screen.getShowing().page);
    }

    static class ScreenThatThrowsExceptionOnLayout extends Page {
        RuntimeException e;
        ScreenThatThrowsExceptionOnLayout(RuntimeException e) {
            super(PageLink.of("name"));
            this.e = e;
        }
        @Override public XContainer layoutForPortrait() {
            throw e;
        }
    }

    @Test
    public void show_logs_exception_if_one_is_thrown() {
        RuntimeException e = new RuntimeException();
        _(log); logManager.getLog(Screen.class);
        _(); wild("*"); log.log("*");
        _(); log.log(e);
        Registry.put(ILogManager.class, logManager);
        Screen screen = Screen.of(link,new ScreenThatThrowsExceptionOnLayout(e));

        screen.show();

        verify();
        log.log(e);
    }

    @Test
    public void show_throws_and_exception_when_factory_returns_no_screens_for_link() {
        PageLink link = PageLink.of("foo");
        Page[] pages = new Page[0];
        _(pages); factory.create(link);

        try {
            Screen.show(link);
            fail();
        } catch (RuntimeException e) {
            assertEquals("No pages found for " + link,e.getMessage());
        }
    }

}