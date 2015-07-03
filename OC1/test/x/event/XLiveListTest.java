package x.event;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks.verify;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class XLiveListTest {

    Change.Listener changeListener;
    XLiveList testObject = new XLiveList();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
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
    public void empty_list_iterates_properly() {
        for (Object o : testObject) {
            fail();
        }
    }

    @Test
    public void one_element_list_iterates_properly() {
        Object item = this;
        testObject.add(item);
        for (Object o : testObject) {
            assertSame(item, o);
        }
    }

    @Test
    public void size_returns_1_for_list_with_1_item() {
        testObject.add("stuff");
        assertEquals(1,testObject.size());
    }

    @Test
    public void listener_is_notified_when_item_is_added() {
        testObject.addListener(changeListener);
        testObject.add("stuff");

        verify();

        changeListener.onChange();
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