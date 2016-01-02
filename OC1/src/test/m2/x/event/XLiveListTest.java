package x.event;

import config.ShouldRun;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mach.Mocks.verify;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class XLiveListTest {

    Change.Listener changeListener;
    XLiveList liveList = XLiveList.of(new ArrayList());

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        Mocks.init(this);
    }

    @Test
    public void can_create() {
        assertNotNull(liveList);
    }

    @Test
    public void is_LiveList() {
        assertTrue(liveList instanceof LiveList);
    }

    @Test
    public void size_returns_0_for_empty_list() {
        assertEquals(0, liveList.size());
    }

    @Test
    public void empty_list_iterates_properly() {
        for (Object o : liveList) {
            fail();
        }
    }

    @Test
    public void one_element_list_iterates_properly() {
        Object item = this;
        liveList.add(item);
        for (Object o : liveList) {
            assertSame(item, o);
        }
    }

    @Test
    public void size_returns_1_for_list_with_1_item() {
        liveList.add("stuff");
        assertEquals(1, liveList.size());
    }

    @Test
    public void listener_is_notified_when_item_is_added() {
        liveList.addListener(changeListener);
        liveList.add("stuff");

        verify();

        changeListener.onChange();
    }

    @Test
    public void toArray_returns_array_with_1_item_when_there_is_one() {
        Object item = this;
        liveList.add(item);

        Object[] array = liveList.toArray();

        assertEquals(1, array.length);
        assertSame(item,array[0]);
    }

    @Test
    public void toString_returns_toString_from_contained_list() {
        List list = Arrays.asList("this","that");
        XLiveList liveList = XLiveList.of(list);
        assertEquals(list.toString(),liveList.toString());
    }
}