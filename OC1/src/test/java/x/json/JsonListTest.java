package x.json;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void toString_gives_the_same_string_as_underlying_list() {
        Json one = JsonValue.of(toString());
        List list = new ArrayList();
        list.add(one);
        JsonList json = JsonList.of(list);

        assertEquals(list.toString(),json.toString());
    }

    @Test
    public void iterator_returns_iterator_with_contents() {
        Json one = JsonValue.of("1st");
        Json two = JsonValue.of("2nd");
        List list = new ArrayList();
        list.add(one);
        list.add(two);
        JsonList json = JsonList.of(list);
        Iterator iterator = json.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(one, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(two, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void empty_lists_are_equal() {
        listsAreEqual(new ArrayList());
    }

    @Test
    public void lists_with_same_contents_are_equal() {
        listsAreEqual(Arrays.asList("this"));
        listsAreEqual(Arrays.asList("that"));
        listsAreEqual(Arrays.asList("the","other"));
    }

    @Test
    public void lists_with_different_contents_are_not_equal() {
        listsAreNotEqual(Arrays.asList("this"), Arrays.asList("that"));
    }

    private void listsAreEqual(List list) {
        JsonList a = JsonList.of(list);
        JsonList b = JsonList.of(list);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    private void listsAreNotEqual(List a, List b) {
        assertNotEquals(a, b);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    private void assertNotEquals(int a, int b) {
        assertFalse(a==b);
    }

    private void assertNotEquals(List a, List b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

}