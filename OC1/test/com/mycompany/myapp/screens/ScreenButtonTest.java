package com.mycompany.myapp.screens;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.fake.FakeUIManager;
import com.mycompany.myapp.ui.ActionButton;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ScreenButtonTest {

    public ScreenButtonTest() {}

    private ActionButton createScreenButtonOnEDT(final String text, final Screen screen) throws Exception {
        return (ActionButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return ScreenButton.of(text, screen);
            }
        });
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
        
        public TestScreen(Form form, Screen previous) {
            super(form, previous);
        }
        
        @Override
        void show() {
            super.show();
            shown = true;
        }
        
        @Override
        protected void refresh() {
            refreshed = true;
        }
    }
    
    private TestScreen createScreenOnEDT() throws Exception {
        return (TestScreen) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                Form form = FakeUI.newForm();
                return new TestScreen(form,null){};
            }
        });
    }

}
