package an.a22.uilist;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.event.Change;
import x.event.XLiveList;
import x.uilist.ListFilter;

import static mach.Mocks.verify;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnListModelAsIListModelTest {

    Change.Listener listener;
    XLiveList liveList = new XLiveList();
    AnVirtualListModel filtered = AnVirtualListModel.of(liveList);
    AnFilterListModel model = AnFilterListModel.of(filtered);
    AnListModelAsIListModel testObject = new AnListModelAsIListModel(model);

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