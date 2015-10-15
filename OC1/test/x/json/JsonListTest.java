package x.json;

import edu.emory.mathcs.backport.java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonListTest {

    @Test
    public void is_a_List() {
        assertTrue(JsonList.of(null) instanceof List);
    }

    @Test
    public void is_Json() {
        assertTrue(JsonList.of(null) instanceof Json);
    }

    @Test
    public void size_is_0_when_list_is_empty() {
        JsonList list = JsonList.of(new ArrayList());
        assertTrue(list.isEmpty());
        assertEquals(0,list.size());
    }

    @Test
    public void list_with_1_item_is_not_empty() {
        Json one = JsonValue.of("one");
        List list = new ArrayList();
        list.add(one);
        JsonList json = JsonList.of(list);
        assertFalse(json.isEmpty());
        assertEquals(1,json.size());
    }

    @Test
    public void size_returns_size_of_underlying_list() {
        List list = new ArrayList();
        for (long i=0; i<3; i++) {
            list.add(JsonValue.of(i));
            JsonList json = JsonList.of(list);
            assertEquals(i + 1,json.size());
        }
    }

    @Test
    public void size_returns_value_at_index_of_given_list() {
        Json one = JsonValue.of("one");
        Json two = JsonValue.of("two");
        Json three = JsonValue.of("three");
        List list = new ArrayList();
        list.add(one);
        list.add(two);
        list.add(three);
        JsonList json = JsonList.of(list);

        assertEquals(one,json.get(0));
        assertEquals(two,json.get(1));
        assertEquals(three,json.get(2));
    }

}