package common.screen.dynamic;

import common.event.StringSource;
import common.screen.Page;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import common.screens.Home;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import fake.FakeSERegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static org.junit.Assert.*;

public class DynamicScreenFactoryTest {

    Object controller=new Home();
    StringSource source1;
    StringSource source2;

    @Before
    public void setUp() {
        Mocks.init(this);
        _("layout{ one }"); source1.getString();
        _("layout{ two }"); source2.getString();
        FakeSERegistryLoader.load();
    }

    @Test
    public void build_produces_ScreenFactory() {
        assertTrue(DynamicScreenFactory.builder().build() instanceof ScreenFactory);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen_array_for_empty_tag_set() {
        ScreenLink link = link("");

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags(""), controller, source1).build();

        Page[] screens = testObject.create(link);

        assertNotNull(screens);
        assertEquals(1,screens.length);
        assertTrue(screens[0] instanceof Page);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen_for_one_tag() {
        ScreenLink link = link("tag1");

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags("tag1"), controller, source1).build();

        Page[] screens = testObject.create(link);

        assertNotNull(screens);
        assertEquals(1,screens.length);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_the_same_link() {
        ScreenLink link = link("");

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags(""), controller, source1).build();
        Page page = testObject.create(link)[0];

        assertSame(link, page.link);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_title_from_screen_link() {

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags("title"), controller, source1).build();
        Page page = testObject.create(link("title"))[0];

        assertSame("title", page.title);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_contents_from_screen_link() {

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags("title"), controller, source1).build();

        UIComponent layout = testObject.create(link("title"))[0].layoutForPortrait();

        UILabel label = (UILabel) layout;
        assertEquals("one",label.text);
    }

    @Test
    public void create_returns_1st_matching_screen_when_there_are_two_choices() {

        ScreenFactory testObject = DynamicScreenFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Page page = testObject.create(link("one"))[0];
        UIComponent layout = page.layoutForPortrait();

        assertSame("one", page.title);
        UILabel label = (UILabel) layout;
        assertEquals("one", label.text);
    }

    @Test
    public void create_returns_2nd_matching_screen_when_there_are_two_choices() {

        ScreenFactory testObject = DynamicScreenFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Page page = testObject.create(link("two"))[0];
        UIComponent layout = page.layoutForPortrait();

        assertSame("two", page.title);
        UILabel label = (UILabel) layout;
        assertEquals("two", label.text);
    }

    @Test
    public void create_returns_both_matching_screen_when_there_are_two_choices() {

        ScreenFactory testObject = DynamicScreenFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Page[] screens = testObject.create(link(""));

        assertEquals(2,screens.length);
    }

    private ScreenTags tags(String string) {
        return ScreenTags.of(string);
    }

    private ScreenLink link(String string) {
        return ScreenLink.of(string);
    }

}