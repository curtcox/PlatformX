package an.a22.uilist;

import fake.FakeDataSetObserver;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import x.event.LiveList;
import x.event.XLiveList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class AnVirtualListModelTest {

    FakeDataSetObserver listDataListener = new FakeDataSetObserver();
    LiveList liveList = new XLiveList();
    AnVirtualListModel testObject = AnVirtualListModel.of(liveList);

    @Before
    public void setUp() {
        Mocks.init(this);
        testObject.registerDataSetObserver(listDataListener);
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
        assertSame(expected,testObject.getItem(0));
    }

    @Test
    public void listener_is_notified_when_list_changes() {
        liveList.add("stuff");

        assertTrue(listDataListener.onChangedCalled);
    }

}