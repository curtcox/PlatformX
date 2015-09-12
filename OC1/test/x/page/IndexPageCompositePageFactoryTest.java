package x.page;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.pagefactories.ItemListPageFactoryFactory;
import x.uiwidget.XComponent;

import static mach.Mocks._;
import static mach.Mocks.wild;
import static org.junit.Assert.*;

public class IndexPageCompositePageFactoryTest  {

    PageLink link = PageLink.of("target");
    Page page = new Page(link) {
        @Override
        public XComponent layoutForPortrait() {
            return null;
        }
    };
    Page indexPage = new Page(link) {
        @Override
        public XComponent layoutForPortrait() {
            return null;
        }
    };
    ItemListPageFactoryFactory itemListPageFactoryFactory;
    PageFactory itemListPageFactory;
    IndexPageCompositePageFactory factory;
    PageFactory inner;

    @Before
    public void setUp() {
        Mocks.init(this);
        Registry.put(ItemListPageFactoryFactory.class, itemListPageFactoryFactory);

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
    public void returns_1_index_page_when_underlying_factory_returns_multiple_pages() {
        _(new Page[2]); inner.create(link);
        _(itemListPageFactory); wild(null,null,null); itemListPageFactoryFactory.newFactory(null,null,null);
        _(new Page[] {indexPage}); itemListPageFactory.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1, actual.length);
        assertSame(indexPage,actual[0]);
    }

}