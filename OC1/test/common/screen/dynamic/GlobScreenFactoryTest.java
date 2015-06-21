package common.screen.dynamic;

import common.screen.Page;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.uiwidget.UIContainer;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class GlobScreenFactoryTest {

    Page screen;
    ScreenFactory testObject = GlobScreenFactory.filter("stuff", new ScreenFactory() {
        @Override
        public Page[] create(ScreenLink link) {
            return new Page[] {screen};
        }
    });
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        screen = new Page(ScreenLink.of("whatever")) {
            @Override
            public UIContainer layoutForPortrait() { return null; }
        };
    }
    
    @Test
    public void create_returns_results_of_doCreate_when_glob_matches() {
         assertSame(screen,testObject.create(ScreenLink.of("stuff"))[0]);
    }

    @Test
    public void create_returns_null_when_glob_does_not_match() {
         assertEquals(null,testObject.create(ScreenLink.of("junk")));
    }

}
