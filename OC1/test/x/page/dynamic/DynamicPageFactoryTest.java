package x.page.dynamic;

import x.event.StringSource;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.page.PageTags;
import x.pages.Home;
import x.uiwidget.XComponent;
import x.uiwidget.XLabel;
import fake.FakeSERegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static org.junit.Assert.*;

public class DynamicPageFactoryTest {

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
        assertTrue(DynamicPageFactory.builder().build() instanceof PageFactory);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen_array_for_empty_tag_set() {
        PageLink link = link("");

        PageFactory testObject = DynamicPageFactory.builder().map(tags(""), controller, source1).build();

        Page[] screens = testObject.create(link);

        assertNotNull(screens);
        assertEquals(1,screens.length);
        assertTrue(screens[0] instanceof Page);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen_for_one_tag() {
        PageLink link = link("tag1");

        PageFactory testObject = DynamicPageFactory.builder().map(tags("tag1"), controller, source1).build();

        Page[] screens = testObject.create(link);

        assertNotNull(screens);
        assertEquals(1,screens.length);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_the_same_link() {
        PageLink link = link("");

        PageFactory testObject = DynamicPageFactory.builder().map(tags(""), controller, source1).build();
        Page page = testObject.create(link)[0];

        assertSame(link, page.link);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_title_from_screen_link() {

        PageFactory testObject = DynamicPageFactory.builder().map(tags("title"), controller, source1).build();
        Page page = testObject.create(link("title"))[0];

        assertSame("title", page.title);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_contents_from_screen_link() {

        PageFactory testObject = DynamicPageFactory.builder().map(tags("title"), controller, source1).build();

        XComponent layout = testObject.create(link("title"))[0].layoutForPortrait();

        XLabel label = (XLabel) layout;
        assertEquals("one",label.text);
    }

    @Test
    public void create_returns_1st_matching_screen_when_there_are_two_choices() {

        PageFactory testObject = DynamicPageFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Page page = testObject.create(link("one"))[0];
        XComponent layout = page.layoutForPortrait();

        assertSame("one", page.title);
        XLabel label = (XLabel) layout;
        assertEquals("one", label.text);
    }

    @Test
    public void create_returns_2nd_matching_screen_when_there_are_two_choices() {

        PageFactory testObject = DynamicPageFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Page page = testObject.create(link("two"))[0];
        XComponent layout = page.layoutForPortrait();

        assertSame("two", page.title);
        XLabel label = (XLabel) layout;
        assertEquals("two", label.text);
    }

    @Test
    public void create_returns_both_matching_screen_when_there_are_two_choices() {

        PageFactory testObject = DynamicPageFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Page[] screens = testObject.create(link(""));

        assertEquals(2,screens.length);
    }

    private PageTags tags(String string) {
        return PageTags.of(string);
    }

    private PageLink link(String string) {
        return PageLink.of(string);
    }

}