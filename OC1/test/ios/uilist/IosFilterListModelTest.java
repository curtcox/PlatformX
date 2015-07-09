package ios.uilist;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.event.XLiveList;
import x.uilist.ListFilter;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class IosFilterListModelTest {

    XLiveList listModel = new XLiveList();

    IosFilterListModel testObject = IosFilterListModel.of(listModel);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.RoboVM);
        Mocks.init(this);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        assertEquals(0, testObject.getNumberOfRowsInSection(null, 0));
    }

    @Test
    public void size_is_1_when_list_has_1_item() {
        listModel.add("stuff");
        assertEquals(1, testObject.getNumberOfRowsInSection(null, 0));
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        listModel.add(expected);
        assertEquals(expected,testObject.getItem(0));
    }

    @Test
    public void size_returns_filtered_size_when_set() {
        listModel.add("stuff");
        testObject.setFilter(ListFilter.ALLOW_NONE);
        assertEquals(0, testObject.getNumberOfRowsInSection(null,0));
    }

    @Test
    public void getElementAt_0_returns_1st_element_that_passes_filter() {
        final Object expected = new Object();
        listModel.add("unexpected");
        listModel.add(expected);
        testObject.setFilter(new ListFilter() {
            @Override
            public boolean passes(Object item) {
                return item==expected;
            }
        });
        assertEquals(expected,testObject.getItem(0));
    }

}