package common.screen.dynamic;

import c1.screens.Home;
import common.Registry;
import common.event.StringSource;
import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.ui.IFormFactory;
import fake.FakeForm;
import fake.FakeFormFactory;
import fake.FakeSERegistryLoader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class DynamicScreenFactoryTest {

    FakeForm form = new FakeForm();
    FakeFormFactory formFactory;
    String name="Display page Title";
    Object controller=new Home();
    StringSource source=new StringSource(){
        @Override
        public String getString() {
            return "layout{}";
        }
    };

    @Before
    public void setUp() {
        FakeSERegistryLoader.load();
        formFactory = (FakeFormFactory) Registry.get(IFormFactory.class);
        formFactory.form = form;
    }

    @Test
    public void build_produces_ScreenFactory() {
        assertTrue(DynamicScreenFactory.builder().build() instanceof ScreenFactory);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_a_screen() {
        ScreenLink link = ScreenLink.of("");
        String globString ="";
        ScreenFactory testObject = DynamicScreenFactory.builder().map(globString, name, controller, source).build();
        Screen screen = testObject.create(link);
        assertNotNull(screen);
        assertSame(link, formFactory.link);
    }

    @Test
    public void map_produces_ScreenFactory_that_maps_to_screens_with_the_same_link() {
        ScreenLink link = ScreenLink.of("");
        String globString ="";
        ScreenFactory testObject = DynamicScreenFactory.builder().map(globString, name, controller, source).build();
        testObject.create(link).layoutForm();

        assertSame(link, form.getScreenLink());
    }

}