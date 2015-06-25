package x.screen;

import fake.FakeUI;
import x.page.Page;
import x.page.PageLink;
import x.pageparts.XScreenButton;
import x.uiwidget.UIButton;
import x.uiwidget.UIComponent;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XScreenButtonTest {

    PageLink link = PageLink.of("");
    TestPage page = new TestPage(link);
    static class TestPage extends Page {

        public boolean shown;
        public boolean refreshed;

        public TestPage(PageLink link) {
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


    private UIButton createScreenButtonOnEDT(final String text, final Page page) throws Exception {
        return (UIButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return XScreenButton.builder().text(text).leadingTo(page).build();
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
