package common.screen.dynamic;

import common.screen.Screen;
import common.screen.ScreenLink;
import common.ui.UIContainer;
import fake.FakeC1RegistryLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GlobScreenFactoryTest {

    Screen screen;
    GlobScreenFactory testObject = new GlobScreenFactory("stuff") {
        @Override
        protected Screen doCreate(ScreenLink link) {
            return screen;
        }
    };
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        screen = new Screen("whatever") {
            @Override
            protected UIContainer layoutForPortrait() { return null; }
        };
    }
    
    @Test
    public void create_returns_results_of_doCreate_when_glob_matches() {
         assertSame(screen,testObject.create(new ScreenLink("stuff")));    
    }

    @Test
    public void create_returns_null_when_glob_does_not_match() {
         assertEquals(null,testObject.create(new ScreenLink("junk")));    
    }

}
