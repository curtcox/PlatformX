package c1.uilist;

import common.uilist.CommonListModel;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class IListModelAsC1ListModelTest {

    CommonListModel model = new CommonListModel();
    IListModelAsC1ListModel testObject = new IListModelAsC1ListModel(model);

    @Test
    public void empty_list_has_size_0() {
        assertEquals(0,testObject.getSize());
    }

    @Test
    public void adding_one_object_to_empty_list_makes_size_1() {
        testObject.addItem("one");

        assertEquals(1, testObject.getSize());
    }

    @Test
    public void getItemAt_returns_item_at_index() {
        Object expected = "item";
        testObject.addItem(expected);

        assertSame(expected, testObject.getItemAt(0));
    }

    @Test
    public void getSelectedIndex_returns_selectedIndexSet() {
        int expected = random();
        testObject.setSelectedIndex(expected);

        assertEquals(expected, testObject.getSelectedIndex());
    }

    private int random() {
        return hashCode();
    }
}