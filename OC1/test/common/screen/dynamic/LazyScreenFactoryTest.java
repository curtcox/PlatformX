package common.screen.dynamic;

import common.event.StringSource;
import common.screen.Page;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import fake.FakeSERegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LazyScreenFactoryTest {

    StringSource source;
    TaggedStringSources sources;
    LazyScreenFactory testObject;

    @Before
    public void setUp() {
        Mocks.init(this);
        FakeSERegistryLoader.load();

        testObject = new LazyScreenFactory(sources);
    }

    @Test
    public void can_create() {
        assertNotNull(new LazyScreenFactory(null));
    }

    @Test
    public void create_returns_empty_array_when_there_are_no_sources() {
        ScreenLink link = ScreenLink.of("");
        _(new StringSource[0]); sources.get(ScreenTags.of(""));

        Page[] actual = testObject.create(link);

        assertNotNull(actual);
        assertEquals(0,actual.length);
    }

    @Test
    public void create_returns_single_matching_screen_when_there_is_one() {
        ScreenLink link = ScreenLink.of("");
        _(new StringSource[]{source}); sources.get(ScreenTags.of(""));

        Page[] actual = testObject.create(link);

        assertNotNull(actual);
        assertEquals(1,actual.length);
    }


}