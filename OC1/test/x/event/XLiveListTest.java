package x.event;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks.verify;
import static org.junit.Assert.*;

public class XLiveListTest {

    Change.Listener listener;
    XLiveList testObject = new XLiveList();

    @Before
    public void setUp() {
        Mocks.init(this);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void is_LiveList() {
        assertTrue(testObject instanceof LiveList);
    }

    @Test
    public void size_returns_0_for_empty_list() {
        assertEquals(0,testObject.size());
    }

    @Test
    public void size_returns_1_for_list_with_1_item() {
        testObject.add("stuff");
        assertEquals(1,testObject.size());
    }

    @Test
    public void listener_is_notified_when_item_is_added() {
        testObject.addListener(listener);
        testObject.add("stuff");

        verify();

        listener.onChange();
    }

    @Test
    public void toArray_returns_array_with_1_item_when_there_is_one() {
        Object item = this;
        testObject.add(item);

        Object[] array = testObject.toArray();

        assertEquals(1, array.length);
        assertSame(item,array[0]);
    }
}