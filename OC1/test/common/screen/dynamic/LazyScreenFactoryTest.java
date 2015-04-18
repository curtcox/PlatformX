package common.screen.dynamic;

import common.event.StringSource;
import common.screen.Screen;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import fake.FakeSERegistryLoader;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LazyScreenFactoryTest {

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

        Screen[] actual = testObject.create(link);

        assertNotNull(actual);
        assertEquals(0,actual.length);
    }
}