package va.uilist;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.event.XLiveList;
import x.uilist.ListFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class VaFilterListModelTest {

    XLiveList listModel = new XLiveList();

    VaFilterListModel testObject = VaFilterListModel.of(listModel);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Vaadin);
        Mocks.init(this);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        assertEquals(0, testObject.size());
    }

    @Test
    public void size_is_1_when_list_has_1_item() {
        listModel.add("stuff");
        assertEquals(1, testObject.size());
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
        assertEquals(0, testObject.size());
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

    @Test
    public void getContainerPropertyIds_returns_first_second_and_icon() {
        Collection actual = testObject.getContainerPropertyIds();
        List list = new ArrayList(actual);

        assertEquals(3,list.size());
        assertEquals("first", list.get(0));
        assertEquals("second",list.get(1));
        assertEquals("icon",  list.get(2));
    }
}