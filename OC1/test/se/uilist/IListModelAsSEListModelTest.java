package se.uilist;

import common.uilist.CommonListModel;
import common.uilist.IListModel;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class IListModelAsSEListModelTest {

    IListModel model = new CommonListModel();
    IListModelAsSEListModel testObject = new IListModelAsSEListModel(model);

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void getSize_returns_0_for_empty_list() {
        assertEquals(0,testObject.getSize());
    }

    @Test
    public void getSize_returns_1_for_list_with_1_item() {
        model.addItem("stuff");
        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getElementAt_returns_element_at_index() {
        Object expected = new Object();
        model.addItem(expected);
        assertSame(expected,testObject.getElementAt(0));
    }

}