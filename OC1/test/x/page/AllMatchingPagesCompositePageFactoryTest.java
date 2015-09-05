package x.page;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;
import x.uiwidget.XComponent;

import static mach.Mocks._;
import static org.junit.Assert.*;

public class AllMatchingPagesCompositePageFactoryTest {

    PageLink link = PageLink.of("target");
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
    ILogManager logManager;
    ILog log;

    @Before
    public void setUp() {
        Mocks.init(this);
        _(log); logManager.getLog(AllMatchingPagesCompositePageFactory.class);
        Registry.put(ILogManager.class,logManager);
    }

    @Test
    public void can_create() {
        assertNotNull(new AllMatchingPagesCompositePageFactory());
    }

    @Test
    public void create_return_an_empty_array_when_there_are_no_factories() {
        PageFactory factory = new AllMatchingPagesCompositePageFactory();

        Page[] actual = factory.create(link);

        assertEquals(0,actual.length);
    }

    @Test
    public void create_returns_page_from_only_factory_when_it_matches() {
        PageFactory factory = new AllMatchingPagesCompositePageFactory(factory1);

        _(new Page[] {page1}); factory1.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page1,actual[0]);
    }

    @Test
    public void create_returns_page_from_1st_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new AllMatchingPagesCompositePageFactory(factory1,factory2);

        _(new Page[] {page1}); factory1.create(link);
        _(new Page[0]);        factory2.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page1,actual[0]);
    }

    @Test
    public void create_returns_page_from_2nd_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new AllMatchingPagesCompositePageFactory(factory1,factory2);

        _(new Page[0]); factory1.create(link);
        _(new Page[] {page1}); factory2.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page1,actual[0]);
    }

    @Test
    public void create_returns_page_from_both_factories_when_they_match() {
        PageFactory factory = new AllMatchingPagesCompositePageFactory(factory1,factory2);

        _(new Page[] {page1}); factory1.create(link);
        _(new Page[] {page2}); factory2.create(link);

        Page[] actual = factory.create(link);

        assertEquals(2,actual.length);
        assertSame(page1, actual[0]);
        assertSame(page2, actual[1]);
    }

}