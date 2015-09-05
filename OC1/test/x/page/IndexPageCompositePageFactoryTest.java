package x.page;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.uiwidget.XComponent;

import static mach.Mocks._;
import static org.junit.Assert.*;

public class IndexPageCompositePageFactoryTest  {

    PageLink link = PageLink.of("target");
    Page page = new Page(link) {
        @Override
        public XComponent layoutForPortrait() {
            return null;
        }
    };
    IndexPageCompositePageFactory factory;
    PageFactory inner;

    @Before
    public void setUp() {
        Mocks.init(this);
        factory = new IndexPageCompositePageFactory(inner);
    }

    @Test
    public void can_create() {
        assertNotNull(new IndexPageCompositePageFactory(inner));
    }

    @Test
    public void is_a_PageFactory() {
        assertTrue(new IndexPageCompositePageFactory(inner) instanceof PageFactory);
    }

    @Test
    public void returns_no_pages_when_underlying_factory_returns_none() {
        _(new Page[0]); inner.create(link);

        Page[] actual = factory.create(link);

        assertEquals(0, actual.length);
    }

    @Test
    public void returns_same_page_as_underlying_factory_does_when_1_page() {
        _(new Page[] {page}); inner.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page, actual[0]);
    }

    @Test
    public void returns_1_page_when_underlying_factory_returns_multiple_pages() {
        _(new Page[2]); inner.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
    }

}