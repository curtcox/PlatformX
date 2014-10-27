package oc1.screen;

import fake.FakeRegistryLoader;
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
        FakeRegistryLoader.load();
        screen = new Screen("whatever") {
            @Override
            protected void layoutForPortrait() {}
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
