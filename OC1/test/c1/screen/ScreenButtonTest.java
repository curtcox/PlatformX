package c1.screen;

import common.screen.Screen;
import common.ui.UIBorderContainer;
import common.ui.UIContainer;
import fake.FakeRegistryLoader;
import c1.screenparts.ScreenButton;
import c1.ui.ActionButton;
import java.util.concurrent.Callable;

import common.ui.IForm;
import c1.screens.FakeUI;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ScreenButtonTest {

    private ActionButton createScreenButtonOnEDT(final String text, final Screen screen) throws Exception {
        return (ActionButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return ScreenButton.textAndLeadingTo(text, screen);
            }
        });
    }

    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void of_returns_ActionButton_with_given_text() throws Exception {
        String text = toString();
        ActionButton button = createScreenButtonOnEDT(text,null);
        assertEquals(text,button.getText());
    }

    @Test
    public void of_returns_ActionButton_that_shows_screen_onTap() throws Exception {
        TestScreen screen = createScreenOnEDT();
        ActionButton button = createScreenButtonOnEDT("text",screen);
        button.onTap();
        assertTrue(screen.shown);
    }

    @Test
    public void of_returns_ActionButton_that_refreshes_screen_onTap() throws Exception {
        TestScreen screen = createScreenOnEDT();
        ActionButton button = createScreenButtonOnEDT("text",screen);
        button.onTap();
        assertTrue(screen.refreshed);
    }

    static class TestScreen extends Screen {

        boolean refreshed;
        boolean shown;
        
        public TestScreen(IForm form) {
            super(form,"test");
        }
        
        @Override
        public void show() {
            super.show();
            shown = true;
        }
        
        @Override
        public void refresh() {
            refreshed = true;
        }

        @Override
        protected UIContainer layoutForPortrait() { return new UIBorderContainer();}
    }
    
    private TestScreen createScreenOnEDT() throws Exception {
        return (TestScreen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                IForm form = FakeUI.newForm();
                return new TestScreen(form){};
            }
        });
    }

}
