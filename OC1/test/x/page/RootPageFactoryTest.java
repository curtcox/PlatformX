package x.page;

import c1.pagefactories.C1ItemListPageFactoryFactory;
import x.Registry;
import x.device.IDeviceInfo;
import x.log.ILogManager;
import x.page.dynamic.StringMapAsTaggedStringSources;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.ui.IFormFactory;
import x.util.SimpleStringMap;
import x.util.StringMap;
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