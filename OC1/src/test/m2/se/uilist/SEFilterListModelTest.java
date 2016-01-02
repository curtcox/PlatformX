package se.uilist;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.event.LiveList;
import x.event.XLiveList;
import x.uilist.ListFilter;

import javax.swing.event.ListDataListener;

import java.util.ArrayList;

import static mach.Mocks.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class SEFilterListModelTest {

    ListDataListener listDataListener;
    LiveList listModel = XLiveList.of(new ArrayList());
    SEFilterListModel testObject = SEFilterListModel.of(listModel);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE);
        Mocks.init(this);
        _(); wild(null); listDataListener.contentsChanged(null);
        testObject.addListDataListener(listDataListener);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        assertEquals(0,testObject.getSize());
    }

    @Test
    public void size_is_1_when_list_has_1_item() {
        listModel.add("stuff");
        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        listModel.add(expected);
        assertEquals(expected,testObject.getElementAt(0));
    }

    @Test
    public void size_returns_filtered_size_when_set() {
        listModel.add("stuff");
        testObject.setFilter(ListFilter.ALLOW_NONE);
        assertEquals(0, testObject.getSize());
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
        assertEquals(expected,testObject.getElementAt(0));
    }

    @Test
    public void dataListener_is_notified_when_filter_changes() {
        testObject.setFilter(ListFilter.ALLOW_NONE);

        verify();

        wild(null); listDataListener.contentsChanged(null);
    }

    @Test
    public void dataListener_is_notified_when_value_added() {
        listModel.add("stuff");

        verify();

        wild(null); listDataListener.contentsChanged(null);
    }
}