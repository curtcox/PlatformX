package common.page;

import c1.pagefactories.C1ItemListPageFactoryFactory;
import common.Registry;
import common.device.IDeviceInfo;
import common.log.ILogManager;
import common.page.dynamic.StringMapAsTaggedStringSources;
import common.page.dynamic.TaggedStringSources;
import common.pagefactories.ItemListPageFactoryFactory;
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

public class RootPageFactoryTest {

    PageFactory testObject;

    @Before
    public void setUp() {
        StringMap stringMap = new SimpleStringMap(null);
        Registry.put(StringMap.class, stringMap);
        Registry.put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        Registry.put(ItemListPageFactoryFactory.class, new C1ItemListPageFactoryFactory());
        Registry.put(IDeviceInfo.class, new FakeDeviceInfo());
        Registry.put(IFormFactory.class, new FakeFormFactory());
        Registry.put(ILogManager.class, new FakeLogManager());
        FakeUIManager.of();
        testObject = RootPageFactory.of();
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void device_info() {
        PageLink link = PageLink.of("Device Info");
        assertEquals(1,screensFrom(link).length);
        Page screen = screensFrom(link)[0];
        assertNotNull(screen);
        assertEquals(link,screen.link);
    }

    @Test
    public void home() {
        PageLink link = PageLink.of("");
        assertEquals(1,screensFrom(link).length);
        Page screen = screensFrom(link)[0];
        assertNotNull(screen);
        assertEquals(link,screen.link);
    }

    private Page[] screensFrom(PageLink link) {
        return testObject.create(link);
    }
}