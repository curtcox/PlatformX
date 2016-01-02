package x.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CollectionAsListTest {

    @Test
    public void size_is_0_when_empty() {
        assertEquals(0,new CollectionAsList(Arrays.asList()).size());
    }

    @Test
    public void size_is_1_when_1_element() {
        assertEquals(1,new CollectionAsList(Arrays.asList(1)).size());
    }

    @Test
    public void get_1_returns_only_element() {
        assertSame(this, new CollectionAsList(Arrays.asList(this)).get(0));
    }

    @Test
    public void get_returns_both_element_when_there_are_2() {
        Set returned = new HashSet();
        List list = new CollectionAsList(Arrays.asList(this,toString()));
        returned.add(list.get(0));
        returned.add(list.get(1));
        assertTrue(returned.contains(this));
        assertTrue(returned.contains(toString()));
    }

}