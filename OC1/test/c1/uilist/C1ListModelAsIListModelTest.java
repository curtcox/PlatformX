package c1.uilist;

import x.event.Change;
import x.event.CommonLiveList;
import x.uilist.ListFilter;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class C1ListModelAsIListModelTest {

    Change.Listener listener;
    CommonLiveList liveList = new CommonLiveList();
    C1VirtualListModel filtered = C1VirtualListModel.of(liveList);
    C1FilterListModel model = C1FilterListModel.of(filtered);
    C1ListModelAsIListModel testObject = new C1ListModelAsIListModel(model);

    @Before
    public void setUp() {
        Mocks.init(this);
        testObject.addListener(listener);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void getSize_returns_0_when_list_is_empty() {
        assertEquals(0,testObject.getSize());
    }

    @Test
    public void getSize_returns_1_when_list_has_1_element() {
        liveList.add("stuff");
        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        liveList.add(expected);
        assertSame(expected,testObject.getItemAt(0));
    }

    @Test
    public void listener_is_notified_when_model_changes() {
        model.setFilter(ListFilter.ALLOW_NONE);

        verify();

        listener.onChange();
    }
}