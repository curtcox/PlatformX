package x.screen;

import config.ShouldRun;
import fake.FakeUI;
import fake.FakeXRegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.page.Page;
import x.page.PageLink;
import x.pageparts.XScreenButton;
import x.uiwidget.XButton;
import x.uiwidget.XComponent;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

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
        public XComponent layoutForPortrait() {
            shown = true;
            return null;
        }

        public void refresh() {
            refreshed = true;
        }
    }

    private XButton createScreenButtonOnEDT(final String text, final Page page) throws Exception {
        return (XButton) FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                return XScreenButton.builder().text(text).leadingTo(page).build();
            }
        });
    }

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        FakeXRegistryLoader.load();
    }

    @Test
    public void of_returns_ActionButton_with_given_text() throws Exception {
        String text = toString();
        XButton button = createScreenButtonOnEDT(text,page);
        assertEquals(text,button.getText());
    }

    @Test
    public void of_returns_ActionButton_that_shows_screen_onTap() throws Exception {
        XButton button = createScreenButtonOnEDT("text",page);
        button.onTap();
        assertTrue(page.shown);
    }

    @Test
    public void of_returns_ActionButton_that_refreshes_screen_onTap() throws Exception {
        XButton button = createScreenButtonOnEDT("text",page);
        button.onTap();
        assertTrue(page.refreshed);
    }

}
