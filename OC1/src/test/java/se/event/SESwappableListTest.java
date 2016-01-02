package se.event;

import mach.Mocks;
import org.junit.Before;
import org.junit.Test;
import x.event.Change;
import x.event.SwappableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mach.Mocks.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SESwappableListTest  {

    Change.Listener listener;
    SwappableList swappableList = new SESwappableList();

    @Before
    public void setUp() {
        Mocks.init(this);
    }

    @Test
    public void is_a_SwappableList() {
        assertTrue(new SESwappableList() instanceof SwappableList);
    }

    @Test
    public void size_is_initially_zero() {
        assertEquals(0, swappableList.size());
    }

    @Test
    public void size_matches_underlying_list_size() {
        List list = new ArrayList();
        swappableList.become(list);
        for (int i=0; i<5; i++) {
            list.add("item");
            assertEquals(list.size(),swappableList.size());
        }
    }

    @Test
    public void get_returns_list_elements_when_list_has_been_set() {
        List list = Arrays.asList(new Object[] {"1","2",this,this.toString()});
        swappableList.become(list);
        for (int i=0; i<list.size(); i++) {
            assertSame(list.get(i),swappableList.get(i));
        }
    }

    @Test
    public void added_listener_is_notified_on_list_change() {
        List list = new ArrayList();
        swappableList.addListener(listener);
        swappableList.become(list);

        verify();

        listener.onChange();
    }

}