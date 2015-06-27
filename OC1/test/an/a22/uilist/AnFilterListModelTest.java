package an.a22.uilist;

import fake.FakeDataSetObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.uilist.ListFilter;
import x.uilist.XListModel;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnFilterListModelTest {

    FakeDataSetObserver listDataListener = new FakeDataSetObserver();
    XListModel xListModel = new XListModel();
    IListModelAsAnListModel underlyingListModel = new IListModelAsAnListModel(xListModel);
    AnFilterListModel testObject = AnFilterListModel.of(underlyingListModel);

    @Before
    public void setUp() {
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
        xListModel.addItem("stuff");
        assertEquals(1, testObject.getCount());
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        xListModel.addItem(expected);
        assertEquals(expected,testObject.getItem(0));
    }

    @Test
    public void size_returns_filtered_size_when_set() {
        xListModel.addItem("stuff");
        testObject.setFilter(ListFilter.ALLOW_NONE);
        assertEquals(0, testObject.getCount());
    }

    @Test
    public void getElementAt_0_returns_1st_element_that_passes_filter() {
        final Object expected = new Object();
        xListModel.addItem("unexpected");
        xListModel.addItem(expected);
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
}