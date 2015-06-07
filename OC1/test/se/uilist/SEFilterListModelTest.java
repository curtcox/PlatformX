package se.uilist;

import common.uilist.CommonListModel;
import common.uilist.ListFilter;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.event.ListDataListener;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SEFilterListModelTest {

    ListDataListener listDataListener;
    CommonListModel commonListModel = new CommonListModel();
    IListModelAsSEListModel underlyingListModel = new IListModelAsSEListModel(commonListModel);
    SEFilterListModel testObject = new SEFilterListModel(underlyingListModel);

    @Before
    public void setUp() {
        Mocks.init(this);
        _(); wild(null); listDataListener.contentsChanged(null);
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
        commonListModel.addItem("stuff");
        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        commonListModel.addItem(expected);
        assertEquals(expected,testObject.getElementAt(0));
    }

    @Test
    public void size_returns_filtered_size_when_set() {
        commonListModel.addItem("stuff");
        testObject.setFilter(ListFilter.ALLOW_NONE);
        assertEquals(0, testObject.getSize());
    }

    @Test
    public void getElementAt_0_returns_1st_element_that_passes_filter() {
        final Object expected = new Object();
        commonListModel.addItem("unexpected");
        commonListModel.addItem(expected);
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
        testObject.addListDataListener(listDataListener);
        testObject.setFilter(ListFilter.ALLOW_NONE);

        verify();

        wild(null); listDataListener.contentsChanged(null);
    }
}