package common.screen;

import common.Registry;
import common.log.ILog;
import common.log.ILogManager;
import common.page.Page;
import common.page.PageFactory;
import common.page.PageLink;
import common.ui.IFormFactory;
import common.uiwidget.UIComponent;
import common.uiwidget.UIContainer;
import fake.FakeCommonRegistryLoader;
import fake.FakeForm;
import fake.FakeFormFactory;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks.*;
import static org.junit.Assert.*;

/**
 * I'm ready for my closeup.
 */
public class CommonScreenTest {

    FakeForm form = new FakeForm();
    String name = random("link");
    UIComponent layout = new UIComponent();
    PageLink link = PageLink.of(name);
    PageLink link1 = PageLink.of("first");
    PageLink link2 = PageLink.of("second");
    PageFactory factory;
    ILog log;
    ILogManager logManager;
    ExamplePage page;
    Screen screen;

    class ExamplePage extends Page {

        public ExamplePage(PageLink link) {
            super(link);
        }

        @Override
        public UIComponent layoutForPortrait() {
            return layout;
        }

        @Override
        public String toString() {
            return link.toString();
        }
    }

    @Before
    public void setup() {
        FakeCommonRegistryLoader.load();
        Registry.put(IFormFactory.class,new FakeFormFactory());
        page = new ExamplePage(link);
        screen = new Screen(form,link,page);
        Mocks.init(this);
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
        Screen first = new Screen(new FakeForm(),link1,new ExamplePage(link1));
        Screen second = new Screen(new FakeForm(),link2,new ExamplePage(link2));

        first.show();
        second.show();

        second.back();

        assertSame(first,Screen.getShowing());
    }

    @Test
    public void back_shows_previously_shown_screen() {
        FakeForm form1 = new FakeForm();
        Screen first = new Screen(form1,link1,new ExamplePage(link1));
        Screen second = new Screen(new FakeForm(),link2,new ExamplePage(link2));

        first.show();
        second.show();

        second.back();

        assertTrue(form1.showBackWasCalled);
    }

    @Test
    public void setBackCommand_is_called_when_there_is_a_previous_screen() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        Screen first = new Screen(form1,link1,new ExamplePage(link1));
        Screen second = new Screen(form2,link2,new ExamplePage(link2));

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
        @Override public UIContainer layoutForPortrait() { return null;}
    }

    @Test
    public void show_makes_page_the_one_showing_when_factory_returns_one_link_for_it() {
        PageLink link = PageLink.of("foo");
        Page page = new FakePage();
        Screen screen = new Screen(new FakeForm(),link,new FakePage());
        Page[] screens = new Page[] { page };
        _(screens); factory.create(link);

        Screen.show(link,factory);

        assertSame(page, Screen.getShowing().page);
    }

    static class ScreenThatThrowsExceptionOnLayout extends Page {
        RuntimeException e;
        ScreenThatThrowsExceptionOnLayout(RuntimeException e) {
            super(PageLink.of("name"));
            this.e = e;
        }
        @Override public UIContainer layoutForPortrait() {
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
        Screen screen = new Screen(new FakeForm(),link,new ScreenThatThrowsExceptionOnLayout(e));

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
            Screen.show(link, factory);
            fail();
        } catch (RuntimeException e) {
            assertEquals("No pages found for " + link,e.getMessage());
        }
    }

}