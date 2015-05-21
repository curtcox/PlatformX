package c1.screenfactories;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ListValueSupplierTest {

    @Test
    public void can_create() {
        assertNotNull(new ListValueSupplier(null));
    }

    @Test
    public void getValues_returns_list_from_constructor() {
        List list = new ArrayList();
        assertSame(list, new ListValueSupplier(list).getValues());
    }

}