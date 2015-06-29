package c1.uilist;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import fake.FakeC1RegistryLoader;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.event.XLiveList;
import x.uilist.ListFilter;

import static mach.Mocks.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class C1FilterListModelTest {

    DataChangedListener listDataListener;
    SelectionListener selectionlistener;

    XLiveList listModel = new XLiveList();
    C1FilterListModel testObject = C1FilterListModel.of(listModel);

    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        Mocks.init(this);
        testObject.addDataChangedListener(listDataListener);
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
        assertEquals(expected,testObject.getItemAt(0));
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
        assertEquals(expected,testObject.getItemAt(0));
    }

    @Test
    public void dataListener_is_notified_when_filter_changes() {
        testObject.setFilter(ListFilter.ALLOW_NONE);

        verify();

        listDataListener.dataChanged(DataChangedListener.CHANGED,-1);
    }

    @Test
    public void dataListener_is_notified_when_value_added() {
        listModel.add("stuff");

        verify();
        listDataListener.dataChanged(DataChangedListener.CHANGED,-1);
    }

    @Test
    public void selectionListener_is_notified_when_selection_is_set() {
        testObject.addSelectionListener(selectionlistener);
        int index = random();
        testObject.setSelectedIndex(index);

        verify();
        selectionlistener.selectionChanged(-1,index);
    }

    @Test
    public void getSelectedIndex_returns_selected_index_set() {
        int index = random();
        testObject.setSelectedIndex(index);

        assertEquals(index,testObject.getSelectedIndex());
    }

    @Test
    public void selectionListener_is_notified_with_old_and_new_indexes_when_selection_is_set() {
        int old = 86;
        int current = 69;
        testObject.setSelectedIndex(old);
        testObject.addSelectionListener(selectionlistener);

        testObject.setSelectedIndex(current);

        verify();
        selectionlistener.selectionChanged(old,current);
    }

    int random() {
        return hashCode();
    }
}