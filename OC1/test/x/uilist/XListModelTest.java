package x.uilist;

import x.event.Change;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static org.junit.Assert.assertNotNull;

public class XListModelTest {

    private Change.Listener listener;
    private XListModel testObject = new XListModel();

    @Before
    public void setUp() {
        Mocks.init(this);
    }

    @Test
    public void can_create() {
        assertNotNull(testObject);
    }

    @Test
    public void addListener_adds_listener_that_is_notified_on_change() {
        testObject.addListener(listener);
        testObject.addItem("1");

        verify();

        listener.onChange();
    }
}