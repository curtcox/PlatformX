package se.uilist;

import common.uilist.CommonListModel;
import junit.framework.TestCase;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SEFilterListModelTest {

    CommonListModel commonListModel = new CommonListModel();
    IListModelAsSEListModel underlyingListModel = new IListModelAsSEListModel(commonListModel);
    SEFilterListModel testObject = new SEFilterListModel(underlyingListModel);

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        assertEquals(0,testObject.getSize());
    }

    @Test
    public void size_is_1_when_list_has_1_item() {
        commonListModel.addItem("stuff");
        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getElementAt_0_returns_1st_element() {
        Object expected = new Object();
        commonListModel.addItem(expected);
        assertEquals(expected,testObject.getElementAt(0));
    }

}