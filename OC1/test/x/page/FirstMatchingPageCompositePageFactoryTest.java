package x.page;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.log.ILog;
import x.log.ILogManager;

import java.util.Arrays;

import static junit.framework.Assert.fail;
import static mach.Mocks._;
import static org.junit.Assert.*;

public class FirstMatchingPageCompositePageFactoryTest {

    Page[] pages = new Page[0];
    PageLink link = PageLink.of("target");
    PageFactory factory1;
    PageFactory factory2;
    ILogManager logManager;
    ILog log;

    @Before
    public void setUp() {
        Mocks.init(this);
        _(log); logManager.getLog(FirstMatchingPageCompositePageFactory.class);
        Registry.put(ILogManager.class,logManager);
    }

    @Test
    public void can_create() {
        assertNotNull(new FirstMatchingPageCompositePageFactory());
    }

    @Test
    public void create_throws_an_exception_when_there_are_no_factories() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory();
        try {
            factory.create(link);
            fail();
        } catch (IllegalArgumentException e) {
            String message = "No screen for " + link + " in " + Arrays.asList();
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void create_returns_link_from_only_factory_when_it_matches() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1);

        _(pages); factory1.create(link);

        Page[] actual = factory.create(link);

        assertSame(actual,pages);
    }

    @Test
    public void create_returns_link_from_1st_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1,factory2);

        _(pages); factory1.create(link);

        Page[] actual = factory.create(link);

        assertSame(actual,pages);
    }

    @Test
    public void create_returns_link_from_2nd_factory_when_it_is_the_only_one_that_matches() {
        PageFactory factory = new FirstMatchingPageCompositePageFactory(factory1,factory2);

        _(null); factory1.create(link);
        _(pages); factory2.create(link);

        Page[] actual = factory.create(link);

        assertSame(actual,pages);
    }

}