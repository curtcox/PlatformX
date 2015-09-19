package x.pagefactorybuilders;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.MatchingIndexedPagesCompositePageFactory;
import x.uiwidget.XComponent;

import static mach.Mocks._;
import static mach.Mocks.wild;
import static org.junit.Assert.*;

public class MatchingIndexedPagesCompositePageFactoryTest {

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
    MatchingIndexedPagesCompositePageFactory factory;

    Page page1 = new Page(link) {
        @Override
        public XComponent layoutForPortrait() {
            return null;
        }
    };
    Page page2 = new Page(link) {
        @Override
        public XComponent layoutForPortrait() {
            return null;
        }
    };
    PageFactory factory1;
    PageFactory factory2;
    PageFactory inner;
    ILogManager logManager;
    ILog log;

    @Before
    public void setUp() {
        Mocks.init(this);
        _(log); logManager.getLog(MatchingIndexedPagesCompositePageFactory.class);
        Registry.put(ItemListPageFactoryFactory.class, itemListPageFactoryFactory);
        Registry.put(ILogManager.class,logManager);
        factory = new MatchingIndexedPagesCompositePageFactory(inner);
    }

    @Test
    public void can_create() {
        assertNotNull(new MatchingIndexedPagesCompositePageFactory());
        assertNotNull(new MatchingIndexedPagesCompositePageFactory(factory1));
        assertNotNull(new MatchingIndexedPagesCompositePageFactory(factory1, factory2));
    }

    @Test
    public void create_return_an_empty_array_when_there_are_no_factories() {
        PageFactory factory = new MatchingIndexedPagesCompositePageFactory();

        Page[] actual = factory.create(link);

        assertEquals(0, actual.length);
    }

    @Test
    public void create_returns_page_from_only_factory_when_it_matches() {
        PageFactory factory = new MatchingIndexedPagesCompositePageFactory(factory1);

        _(new Page[]{page1}); factory1.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1, actual.length);
        assertSame(page1, actual[0]);
    }

    @Test
    public void create_returns_page_from_1st_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new MatchingIndexedPagesCompositePageFactory(factory1,factory2);

        _(new Page[]{page1}); factory1.create(link);
        _(new Page[0]);        factory2.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1, actual.length);
        assertSame(page1, actual[0]);
    }

    @Test
    public void create_returns_page_from_2nd_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new MatchingIndexedPagesCompositePageFactory(factory1,factory2);

        _(new Page[0]); factory1.create(link);
        _(new Page[]{page1}); factory2.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1, actual.length);
        assertSame(page1, actual[0]);
    }

    @Test
    public void create_returns_page_from_both_factories_when_they_match() {
        PageFactory factory = new MatchingIndexedPagesCompositePageFactory(factory1,factory2);

        _(new Page[]{page1}); factory1.create(link);
        _(new Page[]{page2}); factory2.create(link);
        _(itemListPageFactory); wild(null, null, null); itemListPageFactoryFactory.newFactory(null, null, null);
        _(new Page[]{page1, page2}); itemListPageFactory.create(link);

        Page[] actual = factory.create(link);

        assertEquals(2, actual.length);
        assertSame(page1, actual[0]);
        assertSame(page2, actual[1]);
    }

    @Test
    public void is_a_PageFactory() {
        assertTrue(new MatchingIndexedPagesCompositePageFactory(inner) instanceof PageFactory);
    }

    @Test
    public void returns_no_pages_when_underlying_factory_returns_none() {
        _(new Page[0]); inner.create(link);

        Page[] actual = factory.create(link);

        assertEquals(0, actual.length);
    }

    @Test
    public void returns_same_page_as_underlying_factory_does_when_1_page() {
        _(new Page[]{page}); inner.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1, actual.length);
        assertSame(page, actual[0]);
    }

    @Test
    public void returns_1_index_page_when_underlying_factory_returns_multiple_pages() {
        _(new Page[2]); inner.create(link);
        _(itemListPageFactory); wild(null, null, null); itemListPageFactoryFactory.newFactory(null,null,null);
        _(new Page[]{indexPage}); itemListPageFactory.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1, actual.length);
        assertSame(indexPage, actual[0]);
    }

}