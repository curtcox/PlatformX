package common.screen;

import common.Registry;
import common.log.ILogManager;
import common.ui.IFormFactory;
import common.uiwidget.ISearchableList;
import fake.FakeFormFactory;
import fake.FakeLogManager;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.wild;
import static org.junit.Assert.assertNotNull;

public class SelectionListScreenTest {

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
        Registry.put(ILogManager.class, new FakeLogManager());
        Registry.put(IFormFactory.class,new FakeFormFactory());
        testObject = new MySelectionListScreen(link,searchList);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }
}