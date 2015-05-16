package c1.screenfactories;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndexScreenFactoryTest {

    @Test
    public void can_create() {
        assertNotNull(new IndexScreenFactory(new ArrayList<String>()));
    }

    @Test
    public void empty_index_has_no_values() {
        assertEquals(0, new IndexScreenFactory(new ArrayList<String>()).getValues().size());
    }

    @Test
    public void index_with_1_value() {
        String value = "whatever";
        IndexScreenFactory testObject = new IndexScreenFactory(Arrays.asList(new String[]{value}));
        List<String> values = testObject.getValues();
        assertEquals(1, values.size());
        assertEquals(value, values.get(0));
    }

}