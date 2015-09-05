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

public class FirstMatchingPageCompositePageFactoryTest {

    PageLink link = PageLink.of("target");
    Page page = new Page(link) {
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
    Page[] noPages = new Page[0];
    Page[] pages = new Page[] {page};
    PageFactory factory1;
    PageFactory factory2;
    ILogManager logManager;
    ILog log;

    @Before
    public void setUp() {
        Mocks.init(this);
        _(log); logManager.getLog(FirstMatchingPageCompositePageFactory.class);
        Registry.put(ILogManager.class, logManager);
    }

    @Test
    public void can_create() {
        assertNotNull(new FirstMatchingPageCompositePageFactory());
    }

    @Test
    public void create_return_an_empty_array_when_there_are_no_factories() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory();

        Page[] actual = factory.create(link);

        assertEquals(0, actual.length);
    }

    @Test
    public void create_returns_page_from_only_factory_when_it_matches() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1);

        _(pages); factory1.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page,actual[0]);
    }

    @Test
    public void create_returns_first_page_when_factory_has_multiple_matches() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1);

        _(new Page[] {page,page2} ); factory1.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page,actual[0]);
    }

    @Test
    public void create_does_not_consult_more_factories_after_finding_a_match() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1,factory2);

        _(pages); factory1.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page,actual[0]);
    }

    @Test
    public void create_returns_page_from_2nd_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1,factory2);

        _(noPages); factory1.create(link);
        _(pages); factory2.create(link);

        Page[] actual = factory.create(link);

        assertEquals(1,actual.length);
        assertSame(page,actual[0]);
    }

}