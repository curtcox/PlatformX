package se.event;

import common.event.LiveList;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SELiveListTest {

    List list = new ArrayList();
    SELiveList testObject = new SELiveList(list);

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
        list.add("stuff");
        assertEquals(1,testObject.size());
    }

}