package c1.uilist;

import com.codename1.ui.events.DataChangedListener;
import common.event.CommonLiveList;
import common.event.LiveList;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import javax.swing.event.ListDataListener;

import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class C1VirtualListModelTest {

    DataChangedListener listDataListener;
    LiveList liveList = new CommonLiveList();
    C1VirtualListModel testObject = C1VirtualListModel.of(liveList);

    @Before
    public void setUp() {
        Mocks.init(this);
        testObject.addDataChangedListener(listDataListener);
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
    public void listener_is_notified_when_list_changes() {
        liveList.add("stuff");

        verify();

        listDataListener.dataChanged(0,0);
    }

}