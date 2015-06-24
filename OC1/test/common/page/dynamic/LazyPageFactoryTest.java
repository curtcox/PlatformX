package common.page.dynamic;

import common.event.StringSource;
import common.page.Page;
import common.page.PageLink;
import common.page.PageTags;
import fake.FakeSERegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LazyPageFactoryTest {

    StringSource source;
    TaggedStringSources sources;
    LazyPageFactory testObject;

    @Before
    public void setUp() {
        Mocks.init(this);
        FakeSERegistryLoader.load();

        testObject = new LazyPageFactory(sources);
    }

    @Test
    public void can_create() {
        assertNotNull(new LazyPageFactory(null));
    }

    @Test
    public void create_returns_empty_array_when_there_are_no_sources() {
        PageLink link = PageLink.of("");
        _(new StringSource[0]); sources.get(PageTags.of(""));

        Page[] actual = testObject.create(link);

        assertNotNull(actual);
        assertEquals(0,actual.length);
    }

    @Test
    public void create_returns_single_matching_screen_when_there_is_one() {
        PageLink link = PageLink.of("");
        _(new StringSource[]{source}); sources.get(PageTags.of(""));

        Page[] actual = testObject.create(link);

        assertNotNull(actual);
        assertEquals(1,actual.length);
    }


}