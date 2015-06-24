package se.uilist;

import x.Registry;
import x.event.Change;
import x.uilist.ListFilter;
import x.util.Runner;
import fake.FakeRunner;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.event.CommonLiveList;

import static mach.Mocks.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SEListModelAsIListModelTest {

    Change.Listener listener;
    CommonLiveList liveList = new CommonLiveList();
    SEVirtualListModel filtered = SEVirtualListModel.of(liveList);
    SEFilterListModel model = SEFilterListModel.of(filtered);
    SEListModelAsIListModel testObject = new SEListModelAsIListModel(model);

    @Before
    public void setUp() {
        Mocks.init(this);
        Registry.put(Runner.class,new FakeRunner());
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