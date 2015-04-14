package common.screen.dynamic;

import c1.screens.Home;
import common.Registry;
import common.event.StringSource;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.screen.ScreenTags;
import common.ui.IFormFactory;
import common.uiwidget.UIComponent;
import common.uiwidget.UILabel;
import fake.FakeForm;
import fake.FakeFormFactory;
import fake.FakeSERegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static org.junit.Assert.*;

public class DynamicScreenFactoryTest {

    FakeForm form = new FakeForm();
    FakeFormFactory formFactory;
    Object controller=new Home();
    StringSource source1;
    StringSource source2;

    @Before
    public void setUp() {
        Mocks.init(this);
        _("layout{ one }"); source1.getString();
        _("layout{ two }"); source2.getString();
        FakeSERegistryLoader.load();
        formFactory = (FakeFormFactory) Registry.get(IFormFactory.class);
        formFactory.form = form;
    }

    @Test
    public void build_produces_ScreenFactory() {
        assertTrue(DynamicScreenFactory.builder().build() instanceof ScreenFactory);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen_for_empty_tag_set() {
        ScreenLink link = link("");

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags(""), controller, source1).build();

        Screen[] screens = testObject.create(link);

        assertNotNull(screens);
        assertSame(link, formFactory.link);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen_for_one_tag() {
        ScreenLink link = link("tag1");

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags("tag1"), controller, source1).build();

        Screen[] screens = testObject.create(link);

        assertNotNull(screens);
        assertEquals(1,screens.length);
        assertSame(link, formFactory.link);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_the_same_link() {
        ScreenLink link = link("");

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags(""), controller, source1).build();
        testObject.create(link)[0].layoutForm();

        assertSame(link, form.getScreenLink());
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_title_from_screen_link() {

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags("title"), controller, source1).build();
        testObject.create(link("title"))[0].layoutForm();

        assertSame("title", form.getTitle());
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_contents_from_screen_link() {

        ScreenFactory testObject = DynamicScreenFactory.builder().map(tags("title"), controller, source1).build();
        testObject.create(link("title"))[0].layoutForm();

        UIComponent layout = form.layout;
        UILabel label = (UILabel) layout;
        assertEquals("one",label.text);
    }

    @Test
    public void create_returns_1st_matching_screen_when_there_are_two_choices() {

        ScreenFactory testObject = DynamicScreenFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        testObject.create(link("one"))[0].layoutForm();

        assertSame("one", form.getTitle());
        UIComponent layout = form.layout;
        UILabel label = (UILabel) layout;
        assertEquals("one", label.text);
    }

    @Test
    public void create_returns_2nd_matching_screen_when_there_are_two_choices() {

        ScreenFactory testObject = DynamicScreenFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        testObject.create(link("two"))[0].layoutForm();

        assertSame("two", form.getTitle());
        UIComponent layout = form.layout;
        UILabel label = (UILabel) layout;
        assertEquals("two", label.text);
    }

    @Test
    public void create_returns_both_matching_screen_when_there_are_two_choices() {

        ScreenFactory testObject = DynamicScreenFactory.builder()
                .map(tags("one"), controller, source1)
                .map(tags("two"), controller, source2)
                .build();

        Screen[] screens = testObject.create(link(""));

        assertEquals(2,screens.length);
    }

    private ScreenTags tags(String string) {
        return ScreenTags.of(string);
    }

    private ScreenLink link(String string) {
        return ScreenLink.of(string);
    }

}