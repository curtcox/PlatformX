package c1.screenfactories;

import common.screen.Screen;
import common.screen.ScreenLink;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndexScreenFactoryTest {

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
    }

    static ItemListScreenFactory indexScreenFactory(String... values) {
        return IndexScreenFactory.of(Arrays.asList(values));
    }

    @Test
    public void can_create() {
        assertNotNull(indexScreenFactory());
    }

    @Test
    public void empty_index_returns_1_index_screen() {
        ScreenLink link = ScreenLink.of("");
        Screen[] screens = indexScreenFactory().create(link);
        assertEquals(1, screens.length);
    }

    @Test
    public void index_with_3_values_returns_1_screen() {
        ScreenLink link = ScreenLink.of("");
        Screen[] screens = indexScreenFactory("Moe","Larry","Curly").create(link);
        assertEquals(1, screens.length);
    }

    @Test
    public void index_with_1_value_can_create_1_screen() {
        ScreenLink link = ScreenLink.of("moe");
        Screen[] screens = indexScreenFactory("moe").create(link);
        assertEquals(1, screens.length);
        Screen screen = screens[0];
        assertEquals("moe", screen.link.tags.toString());
    }

    @Test
    public void index_with_3_values_can_create_3_screens() {
        ItemListScreenFactory factory = indexScreenFactory("moe", "larry", "curly");
        checkScreenFrom(factory, "moe");
        checkScreenFrom(factory,"larry");
        checkScreenFrom(factory, "curly");
    }

    void checkScreenFrom(ItemListScreenFactory factory,String tag) {
        Screen[] screens = factory.create(ScreenLink.of(tag));
        assertEquals(1, screens.length);
        Screen screen = screens[0];
        assertEquals(tag,screen.link.tags.toString());
    }

}