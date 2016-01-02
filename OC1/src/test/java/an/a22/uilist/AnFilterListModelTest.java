package an.a22.uilist;

import config.ShouldRun;
import fake.FakeDataSetObserver;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.event.XLiveList;
import x.uilist.IXListCell;
import x.uilist.ListFilter;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnFilterListModelTest {

    FakeDataSetObserver listDataListener = new FakeDataSetObserver();
    IXListCell.ConfigProducer configurer;
    XLiveList listModel = XLiveList.of(new ArrayList());
    AnFilterListModel testObject;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Android);
        Mocks.init(this);
        testObject = AnFilterListModel.of(listModel,configurer);
        testObject.registerDataSetObserver(listDataListener);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        assertEquals(0,testObject.getCount());
    }

    @Test
    public void size_is_1_when_list_has_1_item() {
        listModel.add("stuff");
        assertEquals(1, testObject.getCount());
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
        assertEquals(0, testObject.getCount());
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
    public void dataListener_is_notified_when_filter_changes() {
        testObject.setFilter(ListFilter.ALLOW_NONE);

        assertTrue(listDataListener.onChangedCalled);
    }

    @Test
    public void dataListener_is_notified_when_value_added() {
        listModel.add("stuff");

        assertTrue(listDataListener.onChangedCalled);
    }

    @Test
    public void areAllItemsEnabled_returns_true() {
        assertTrue(testObject.areAllItemsEnabled());
    }

    @Test
    public void isEnabled_returns_true() {
        assertTrue(testObject.isEnabled(0));
    }

    @Test
    public void getViewTypeCount_returns_1() {
        assertEquals(1,testObject.getViewTypeCount());
    }

    @Test
    public void getItemViewType_returns_0() {
        assertEquals(0,testObject.getItemViewType(0));
    }
}