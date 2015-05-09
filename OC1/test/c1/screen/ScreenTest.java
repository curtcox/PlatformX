package c1.screen;

import common.Registry;
import common.log.ILog;
import common.log.ILogManager;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.uiwidget.UIContainer;
import fake.FakeC1RegistryLoader;
import java.util.concurrent.Callable;

import common.ui.IForm;
import c1.screens.FakeUI;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.*;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

public class ScreenTest {
    
    IForm form;
    Screen previous;
    ScreenFactory factory;
    ILog log;
    ILogManager logManager;

    static class FakeScreen extends Screen {
        FakeScreen() {
            super(FakeUI.newForm(), ScreenLink.of("name"));
        }
        @Override protected UIContainer layoutForPortrait() { return null;}
    }

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        Mocks.init(this);
    }

    @Test
    public void can_create() {
        new FakeScreen();
    }
    
    @Test
    public void can_create_with_form() throws Exception {
        assertNotNull(createScreenOnEDT(previous));
    }
    
    private Screen createScreenOnEDT(final Screen previous) throws Exception {
        return (Screen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                form = FakeUI.newForm();
                return new FakeScreen();
            }
        });
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
        Registry.put(ILogManager.class,logManager);
        Screen screen = new ScreenThatThrowsExceptionOnLayout(e);

        screen.show();

        verify();
        log.log(e);
    }

}
