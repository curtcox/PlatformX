package x.page;

import config.ShouldRun;
import fake.FakeFormFactory;
import fake.FakeLogManager;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.log.ILogManager;
import x.ui.IFormFactory;
import x.uiwidget.XPeeredComponent;
import x.uiwidget.XSearchableList;

import static mach.Mocks._;
import static mach.Mocks.wild;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SelectionListPageTest {

    Object peer = new Object();
    PageLink link = PageLink.of("");
    XSearchableList searchList;
    MySelectionListPage testObject;

    static final class MySelectionListPage extends SelectionListPage {

        public MySelectionListPage(PageLink link, XSearchableList searchList) {
            super(link, searchList);
        }

        @Override
        protected PageLink useSelectedItem(Object item) {
            return null;
        }
    }

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        Mocks.init(this);
        _(); wild(null); searchList.onSelected(null);
        _(peer); searchList.getComponent();

        Registry.put(ILogManager.class, new FakeLogManager());
        Registry.put(IFormFactory.class,new FakeFormFactory());
        testObject = new MySelectionListPage(link,searchList);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void layoutForPortrait_returns_a_peered_component() {
        assertTrue(testObject.layoutForPortrait() instanceof XPeeredComponent);
    }

    @Test
    public void layoutForPortrait_returns_component_from_searchList() {
        XPeeredComponent peeredComponent = testObject.layoutForPortrait();

        assertSame(peer,peeredComponent.peer);
    }

}