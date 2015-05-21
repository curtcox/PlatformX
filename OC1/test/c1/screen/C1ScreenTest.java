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

public class C1ScreenTest {
    
    IForm form;
    Screen previous;

    static class FakeScreen extends Screen {
        FakeScreen() {
            super(FakeUI.newForm(), ScreenLink.of("name"));
        }
        @Override protected UIContainer layoutForPortrait() { return null;}
    }

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
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


}