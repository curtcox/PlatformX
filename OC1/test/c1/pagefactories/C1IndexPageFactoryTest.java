package c1.pagefactories;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.event.XLiveList;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.pagefactories.IndexPageFactory;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class C1IndexPageFactoryTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeC1RegistryLoader.load();
    }

    static PageFactory indexScreenFactory(String... values) {
        return IndexPageFactory.of(null,XLiveList.of(Arrays.asList(values)));
    }

    @Test
    public void can_create() {
        assertNotNull(indexScreenFactory());
    }

    @Test
    public void empty_index_returns_1_index_screen() {
        PageLink link = PageLink.of("");
        Page[] screens = indexScreenFactory().create(link);
        assertEquals(1, screens.length);
    }

    @Test
    public void index_with_3_values_returns_1_screen() {
        PageLink link = PageLink.of("");
        Page[] screens = indexScreenFactory("Moe","Larry","Curly").create(link);
        assertEquals(1, screens.length);
    }

    @Test
    public void index_with_1_value_can_create_1_screen() {
        PageLink link = PageLink.of("moe");
        Page[] screens = indexScreenFactory("moe").create(link);
        assertEquals(1, screens.length);
        Page screen = screens[0];
        assertEquals("moe", screen.link.tags.toString());
    }

    @Test
    public void index_with_3_values_can_create_3_screens() {
        PageFactory factory = indexScreenFactory("moe", "larry", "curly");
        checkScreenFrom(factory, "moe");
        checkScreenFrom(factory,"larry");
        checkScreenFrom(factory, "curly");
    }

    void checkScreenFrom(PageFactory factory,String tag) {
        Page[] screens = factory.create(PageLink.of(tag));
        assertEquals(1, screens.length);
        Page screen = screens[0];
        assertEquals(tag,screen.link.tags.toString());
    }

}