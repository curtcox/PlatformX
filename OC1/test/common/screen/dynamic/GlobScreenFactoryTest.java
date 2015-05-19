package common.screen.dynamic;

import common.screen.Screen;
import common.screen.ScreenFactory;
import common.screen.ScreenLink;
import common.uiwidget.UIContainer;
import fake.FakeC1RegistryLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GlobScreenFactoryTest {

    Screen screen;
    ScreenFactory testObject = GlobScreenFactory.filter("stuff", new ScreenFactory() {
        @Override
        public Screen[] create(ScreenLink link) {
            return new Screen[] {screen};
        }
    });
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        screen = new Screen(ScreenLink.of("whatever")) {
            @Override
            protected UIContainer layoutForPortrait() { return null; }
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
