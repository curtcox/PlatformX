package x.page;

import config.ShouldRun;
import fake.*;
import org.junit.Before;
import org.junit.Test;
import x.app.Registry;
import x.app.RootPageFactory;
import x.device.XDeviceInfo;
import x.event.NamedValueListSource;
import x.log.ILogManager;
import x.log.XLogWriter;
import x.page.dynamic.StringMapAsTaggedStringSources;
import x.page.dynamic.TaggedStringSources;
import x.pagefactories.ItemListPageFactoryFactory;
import x.pagefactories.XItemListPageFactoryFactory;
import x.ui.IFormFactory;
import x.uiwidget.XSearchableList;
import x.util.SimpleStringMap;
import x.util.StringMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class RootPageFactoryTest {

    PageFactory testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        StringMap stringMap = new SimpleStringMap(null);
        Registry.put(StringMap.class, stringMap);
        Registry.put(TaggedStringSources.class, new StringMapAsTaggedStringSources(stringMap));
        Registry.put(ItemListPageFactoryFactory.class, new XItemListPageFactoryFactory());
        Registry.put(XDeviceInfo.class, new FakeDeviceInfo());
        Registry.put(IFormFactory.class, new FakeFormFactory());
        Registry.put(ILogManager.class, new FakeLogManager());
        Registry.put(XLogWriter.class, new XLogWriter());
        Registry.put(XSearchableList.Factory.class, new FakeSearchableListFactory());
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