package se.screen;

import common.Registry;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.dynamic.StringMapAsTaggedStringSources;
import common.screen.dynamic.TaggedStringSources;
import common.util.SimpleStringMap;
import common.util.StringMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SERootScreenFactoryTest {

    ScreenFactory testObject;

    @Before
    public void setUp() {
        StringMap stringMap = new SimpleStringMap(null);
        Registry.put(StringMap.class, stringMap);
        Registry.put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        testObject = SERootScreenFactory.of();
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void device_info() {
        ScreenLink link = ScreenLink.of("Device Info");
        assertEquals(1,screensFrom(link).length);
        Screen screen = screensFrom(link)[0];
        assertNotNull(screen);
        assertEquals(link,screen.link);
    }

    @Test
    public void home() {
        ScreenLink link = ScreenLink.of("");
        assertEquals(1,screensFrom(link).length);
        Screen screen = screensFrom(link)[0];
        assertNotNull(screen);
        assertEquals(link,screen.link);
    }

    private Screen[] screensFrom(ScreenLink link) {
        return testObject.create(link);
    }
}