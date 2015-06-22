package c1.screen;

import c1.screens.FakeUI;
import common.screen.Page;
import common.screen.ScreenLink;
import common.screenparts.ScreenButton;
import common.uiwidget.UIButton;
import common.uiwidget.UIComponent;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C1ScreenButtonTest {

    static class TestPage extends Page {

        public boolean shown;
        public boolean refreshed;

        public TestPage(ScreenLink link) {
            super(link);
        }

        @Override
        public UIComponent layoutForPortrait() {
            shown = true;
            return null;
        }

        public void refresh() {
            refreshed = true;
        }
    }

    ScreenLink link = ScreenLink.of("");
    TestPage page = new TestPage(link);

    private UIButton createScreenButtonOnEDT(final String text, final Page page) throws Exception {
        return (UIButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return ScreenButton.builder().text(text).leadingTo(page).build();
            }
        });
    }

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void of_returns_ActionButton_with_given_text() throws Exception {
        String text = toString();
        UIButton button = createScreenButtonOnEDT(text,page);
        assertEquals(text,button.getText());
    }

    @Test
    public void of_returns_ActionButton_that_shows_screen_onTap() throws Exception {
        UIButton button = createScreenButtonOnEDT("text",page);
        button.onTap();
        assertTrue(page.shown);
    }

    @Test
    public void of_returns_ActionButton_that_refreshes_screen_onTap() throws Exception {
        UIButton button = createScreenButtonOnEDT("text",page);
        button.onTap();
        assertTrue(page.refreshed);
    }

}
