package common.screen;

import c1.screens.FakeUI;
import common.Registry;
import common.log.ILog;
import common.log.ILogManager;
import common.ui.IForm;
import common.uiwidget.UIComponent;
import common.uiwidget.UIContainer;
import fake.FakeCommonRegistryLoader;
import fake.FakeForm;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.*;

/**
 * I'm ready for my closeup.
 */
public class CommonScreenTest {

    FakeForm form = new FakeForm();
    String name = random("link");
    UIComponent layout = new UIComponent();
    ScreenLink link = ScreenLink.of(name);
    ScreenFactory factory;
    ILog log;
    ILogManager logManager;
    ExampleScreen testObject;

    class ExampleScreen extends Screen {

        public ExampleScreen(IForm form,ScreenLink link) {
            super(form,link);
        }

        @Override
        protected UIComponent layoutForPortrait() {
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
        testObject = new ExampleScreen(form,link);
        Mocks.init(this);
    }


    @Test
    public void can_create() {
        assertNotNull(new ExampleScreen(form,link));
    }

    @Test
    public void uses_screenLink_from_constructor() {
        assertSame(link,testObject.link);
    }

    @Test
    public void show_sets_form_layout() {
        testObject.show();

        assertSame(layout, form.layout);
    }

    @Test
    public void refresh_sets_form_layout() {
        testObject.refresh();

        assertSame(layout, form.layout);
    }

    @Test
    public void show_sets_showing_screen() {
        testObject.show();

        assertSame(testObject,Screen.getShowing());
    }

    @Test
    public void show_calls_show_on_form() {
        testObject.show();

        assertTrue(form.showWasCalled);
    }

    @Test
    public void getShowing_returns_original_screen_after_going_back_to_it() {
        ExampleScreen first = new ExampleScreen(new FakeForm(),ScreenLink.of("first"));
        ExampleScreen second = new ExampleScreen(new FakeForm(),ScreenLink.of("second"));

        first.show();
        second.show();

        second.back();

        assertSame(first,Screen.getShowing());
    }

    @Test
    public void back_shows_previously_shown_screen() {
        FakeForm form1 = new FakeForm();
        ExampleScreen first = new ExampleScreen(form1,ScreenLink.of("first"));
        ExampleScreen second = new ExampleScreen(new FakeForm(),ScreenLink.of("second"));

        first.show();
        second.show();

        second.back();

        assertTrue(form1.showBackWasCalled);
    }

    @Test
    public void setBackCommand_is_called_when_there_is_a_previous_screen() {
        FakeForm form1 = new FakeForm();
        FakeForm form2 = new FakeForm();
        ExampleScreen first = new ExampleScreen(form1,ScreenLink.of("first"));
        ExampleScreen second = new ExampleScreen(form2,ScreenLink.of("second"));

        first.show();
        second.show();

        second.back();

        assertEquals("Back", form2.getBackCommand().command);
    }

    private String random(String name) {
        return name + toString();
    }

    static class FakeScreen extends Screen {
        FakeScreen() {
            super(FakeUI.newForm(), ScreenLink.of("name"));
        }
        @Override protected UIContainer layoutForPortrait() { return null;}
    }

    @Test
    public void show_makes_screen_the_one_showing_when_factory_returns_one_link_for_it() {
        Screen screen = new FakeScreen();
        ScreenLink link = ScreenLink.of("foo");
        Screen[] screens = new Screen[] { screen };
        _(screens); factory.create(link);

        Screen.show(link,factory);

        assertSame(screen, Screen.getShowing());
    }

    static class ScreenThatThrowsExceptionOnLayout extends Screen {
        RuntimeException e;
        ScreenThatThrowsExceptionOnLayout(RuntimeException e) {
            super(FakeUI.newForm(), ScreenLink.of("name"));
            this.e = e;
        }
        @Override protected UIContainer layoutForPortrait() {
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
        Screen screen = new ScreenThatThrowsExceptionOnLayout(e);

        screen.show();

        verify();
        log.log(e);
    }

    @Test
    public void show_throws_and_exception_when_factory_returns_no_screens_for_link() {
        ScreenLink link = ScreenLink.of("foo");
        Screen[] screens = new Screen[0];
        _(screens); factory.create(link);

        try {
            Screen.show(link, factory);
            fail();
        } catch (RuntimeException e) {
            assertEquals("No screens found for " + link,e.getMessage());
        }
    }

}