package se.uilist;

import common.event.LiveList;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import se.event.SELiveList;

import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SEVirtualListModelTest {

    ListDataListener listDataListener;
    List list = new ArrayList();
    SELiveList liveList = new SELiveList(list);
    SEVirtualListModel testObject = SEVirtualListModel.of(liveList);

    @Before
    public void setUp() {
        Mocks.init(this);
        testObject.addListDataListener(listDataListener);
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
        list.add("stuff");
        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        list.add(expected);
        assertSame(expected,testObject.getElementAt(0));
    }

    @Test
    public void listener_is_notified_when_list_changes() {
        list.add("stuff");

        verify();

        wild(null); listDataListener.contentsChanged(null);
    }

}