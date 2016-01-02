package x.page.dynamic;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.page.Page;
import x.page.PageFactory;
import x.page.PageLink;
import x.uiwidget.XContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeTrue;

public class GlobPageFactoryTest {

    Page screen;
    PageFactory testObject = GlobPageFactory.filter("stuff", new PageFactory() {
        @Override
        public Page[] create(PageLink link) {
            return new Page[]{screen};
        }
    });
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        FakeC1RegistryLoader.load();
        screen = new Page(PageLink.of("whatever")) {
            @Override
            public XContainer layoutForPortrait() { return null; }
        };
    }

    @Test
    public void create_returns_results_of_doCreate_when_glob_matches() {
         assertSame(screen,testObject.create(PageLink.of("stuff"))[0]);
    }

    @Test
    public void create_returns_null_when_glob_does_not_match() {
         assertEquals(null,testObject.create(PageLink.of("junk")));
    }

}
