package c1.screenfactories;

import common.screen.ScreenLink;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndexScreenFactoryTest {

    static IndexScreenFactory indexScreenFactory(String... values) {
        return new IndexScreenFactory(Arrays.asList(values));
    }

    @Test
    public void can_create() {
        assertNotNull(indexScreenFactory());
    }

    @Test
    public void empty_index_has_no_values() {
        assertEquals(0, indexScreenFactory().getValues().size());
    }

    @Test
    public void empty_index_has_screens() {
        assertEquals(0, indexScreenFactory().create(ScreenLink.of("")).length);
    }

    @Test
    public void index_with_1_value() {
        String value = "whatever";
        IndexScreenFactory testObject = indexScreenFactory(value);
        List<String> values = testObject.getValues();
        assertEquals(1, values.size());
        assertEquals(value, values.get(0));
    }

}