package common.screen;

import c1.screenfactories.C1ItemListScreenFactoryFactory;
import common.Registry;
import common.device.IDeviceInfo;
import common.log.ILogManager;
import common.screen.dynamic.StringMapAsTaggedStringSources;
import common.screen.dynamic.TaggedStringSources;
import common.screenfactories.ItemListScreenFactoryFactory;
import common.ui.IFormFactory;
import common.util.SimpleStringMap;
import common.util.StringMap;
import fake.FakeDeviceInfo;
import fake.FakeFormFactory;
import fake.FakeLogManager;
import fake.FakeUIManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RootScreenFactoryTest {

    ScreenFactory testObject;

    @Before
    public void setUp() {
        StringMap stringMap = new SimpleStringMap(null);
        Registry.put(StringMap.class, stringMap);
        Registry.put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        Registry.put(ItemListScreenFactoryFactory.class, new C1ItemListScreenFactoryFactory());
        Registry.put(IDeviceInfo.class, new FakeDeviceInfo());
        Registry.put(IFormFactory.class, new FakeFormFactory());
        Registry.put(ILogManager.class, new FakeLogManager());
        FakeUIManager.of();
        testObject = RootScreenFactory.of();
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void device_info() {
        ScreenLink link = ScreenLink.of("Device Info");
        assertEquals(1,screensFrom(link).length);
        Page screen = screensFrom(link)[0];
        assertNotNull(screen);
        assertEquals(link,screen.link);
    }

    @Test
    public void home() {
        ScreenLink link = ScreenLink.of("");
        assertEquals(1,screensFrom(link).length);
        Page screen = screensFrom(link)[0];
        assertNotNull(screen);
        assertEquals(link,screen.link);
    }

    private Page[] screensFrom(ScreenLink link) {
        return testObject.create(link);
    }
}