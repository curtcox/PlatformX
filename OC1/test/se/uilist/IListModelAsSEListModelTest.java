package se.uilist;

import x.uilist.XListModel;
import x.uilist.IListModel;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import javax.swing.event.ListDataListener;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static mach.Mocks.wild;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class IListModelAsSEListModelTest {

    ListDataListener listDataListener;
    IListModel model = new XListModel();
    IListModelAsSEListModel testObject = new IListModelAsSEListModel(model);

    @Before
    public void setUp() {
        Mocks.init(this);
        _(); wild(null); listDataListener.contentsChanged(null);
        testObject.addListDataListener(listDataListener);
    }

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

    @Test
    public void addListDataListener_adds_a_listener_that_is_notified_when_model_changes() {
        model.addItem("");

        verify();
        wild(null); listDataListener.contentsChanged(null);
    }
}