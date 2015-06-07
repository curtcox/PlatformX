package se.uilist;

import junit.framework.TestCase;
import org.junit.Test;
import se.event.SELiveList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SEListModelAsIListModelTest {

    List list = new ArrayList();
    SELiveList liveList = new SELiveList(list);
    SEVirtualListModel filtered = SEVirtualListModel.of(liveList);
    SEFilterListModel model = new SEFilterListModel(filtered);
    SEListModelAsIListModel testObject = new SEListModelAsIListModel(model);

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
        assertSame(expected,testObject.getItemAt(0));
    }
}