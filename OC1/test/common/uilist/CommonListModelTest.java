package common.uilist;

import common.event.Change;
import junit.framework.TestCase;
import mach.Mocks;
import org.junit.Before;
import org.junit.Test;

import static mach.Mocks._;
import static mach.Mocks.verify;
import static org.junit.Assert.assertNotNull;

public class CommonListModelTest {

    private Change.Listener listener;
    private CommonListModel testObject = new CommonListModel();

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