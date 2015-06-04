package common.screen;

import common.Registry;
import common.log.ILogManager;
import common.ui.IFormFactory;
import common.uiwidget.ISearchableList;
import common.uiwidget.UIPeeredComponent;
import fake.FakeFormFactory;
import fake.FakeLogManager;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.wild;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SelectionListScreenTest {

    Object peer = new Object();
    ScreenLink link;
    ISearchableList searchList;
    MySelectionListScreen testObject;

    static final class MySelectionListScreen extends SelectionListScreen {

        public MySelectionListScreen(ScreenLink link, ISearchableList searchList) {
            super(link, searchList);
        }

        @Override
        protected ScreenLink useSelectedItem(Object item) {
            return null;
        }
    }

    @Before
    public void setUp() {
        Mocks.init(this);
        _(); wild(null); searchList.onSelected(null);
        _(peer); searchList.getComponent();

        Registry.put(ILogManager.class, new FakeLogManager());
        Registry.put(IFormFactory.class,new FakeFormFactory());
        testObject = new MySelectionListScreen(link,searchList);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void layoutForPortrait_returns_a_peered_component() {
        assertTrue(testObject.layoutForPortrait() instanceof UIPeeredComponent);
    }

    @Test
    public void layoutForPortrait_returns_component_from_searchList() {
        UIPeeredComponent peeredComponent = testObject.layoutForPortrait();

        assertSame(peer,peeredComponent.peer);
    }

}