package x.page;

import org.junit.Test;
import x.uiwidget.XComponent;

import static org.junit.Assert.assertSame;

public class PageTest {

    @Test
    public void link_is_link_from_constructor() {
        PageLink link = PageLink.of("foo");
        Page page = new Page(link) {
            @Override
            public XComponent layoutForPortrait() {
                return null;
            }
        };

        assertSame(link,page.link);
    }

    @Test
    public void withFixedLayout_returns_page_and_link_that_match() {
        Page page = Page.withFixedLayout("", null);
        assertSame(page,     page.link.page);
        assertSame(page.link,page.link.page.link);
    }

    @Test
    public void withFixedLayout_returns_page_with_given_title() {
        String title = random();
        Page page = Page.withFixedLayout(title, null);
        assertSame(title,page.title);
    }

    @Test
    public void withFixedLayout_returns_page_with_given_layout() {
        XComponent layout = new XComponent();
        Page page = Page.withFixedLayout("", layout);
        assertSame(layout,page.layoutForLandscape());
        assertSame(layout,page.layoutForPortrait());
    }

    private String random() {
        return toString();
    }

}