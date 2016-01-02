package x.json;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonMapTest {

    @Test
    public void is_Json() {
        assertTrue(JsonMap.of(new HashMap()) instanceof Json);
    }

    @Test
    public void is_Map() {
        assertTrue(JsonMap.of(new HashMap()) instanceof Map);
    }

    @Test
    public void is_empty_when_size_is_zero() {
        JsonMap map = JsonMap.of(new HashMap());
        assertEquals(0,map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void keySet_is_empty_when_size_is_zero() {
        JsonMap map = JsonMap.of(new HashMap());
        assertTrue(map.keySet().isEmpty());
    }

    @Test
    public void entrySet_is_empty_when_size_is_zero() {
        JsonMap map = JsonMap.of(new HashMap());
        assertTrue(map.keySet().isEmpty());
    }

    @Test
    public void values_set_is_empty_when_size_is_zero() {
        JsonMap map = JsonMap.of(new HashMap());
        assertTrue(map.values().isEmpty());
    }

    @Test
    public void is_not_empty_when_size_is_one() {
        HashMap hash = new HashMap();
        hash.put("key",JsonValue.of("value"));
        JsonMap map = JsonMap.of(hash);
        assertEquals(1,map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    public void containsKey_returns_true_for_keys_in_map() {
        HashMap hash = new HashMap();
        hash.put("key",JsonValue.of("value"));
        JsonMap map = JsonMap.of(hash);
        assertFalse(map.containsKey("value"));
        assertTrue(map.containsKey("key"));
    }

    @Test
    public void get_returns_value_for_keys_in_map() {
        HashMap hash = new HashMap();
        hash.put("key",JsonValue.of("value"));
        JsonMap map = JsonMap.of(hash);
        assertEquals(JsonValue.of("value"),map.get("key"));
        assertEquals(null, map.get("value"));
    }

    @Test
    public void containsValue_returns_true_for_values_in_map() {
        HashMap hash = new HashMap();
        hash.put("key",JsonValue.of("value"));
        JsonMap map = JsonMap.of(hash);
        assertFalse(map.containsValue("key"));
        assertTrue(map.containsValue(JsonValue.of("value")));
    }

    @Test
    public void toString_produces_same_string_as_underlying_map() {
        HashMap hash = new HashMap();
        hash.put("key",JsonValue.of("value"));
        JsonMap map = JsonMap.of(hash);

        assertEquals(hash.toString(), map.toString());
    }

    @Test
    public void empty_maps_are_equal() {
        mapsAreEqual(new HashMap());
    }

    @Test
    public void equal_maps_are_equal() {
        HashMap hash = new HashMap();
        hash.put("key",JsonValue.of("value"));
        mapsAreEqual(hash);
    }

    @Test
    public void unequal_maps_are_not_equal() {
        HashMap a = new HashMap();
        a.put("k",JsonValue.of("1"));
        HashMap b = new HashMap();
        a.put("k", JsonValue.of("2"));
        mapsAreNotEqual(a,b);
    }

    void mapsAreEqual(Map map) {
        JsonMap a = JsonMap.of(map);
        JsonMap b = JsonMap.of(map);
        assertEquals(a.hashCode(),b.hashCode());
        assertEquals(a,b);
    }

    void mapsAreNotEqual(Map map1,Map map2) {
        JsonMap a = JsonMap.of(map1);
        JsonMap b = JsonMap.of(map2);
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);
    }

    @Test
    public void string_returns_null_when_map_has_no_value_for_key() {
        JsonMap map = JsonMap.of(new HashMap());
        assertNull(map.string("key"));
    }

    @Test
    public void string_returns_string_value_when_map_has_string_value_for_key() {
        HashMap hash = new HashMap();
        String value = toString();
        JsonValue json = JsonValue.of(value);
        String key = "key";
        hash.put(key,json);
        JsonMap map = JsonMap.of(hash);
        assertSame(value, map.string(key));
    }

    @Test
    public void URI_returns_URI_value_when_map_has_URI_value_for_key() throws URISyntaxException {
        HashMap hash = new HashMap();
        URI uri = new URI("example.com");
        JsonValue json = JsonValue.of("example.com");
        String key = "key";
        hash.put(key,json);
        JsonMap map = JsonMap.of(hash);
        assertEquals(uri, map.uri(key));
    }

    @Test
    public void long_returns_long_value_when_map_has_long_value_for_key() throws URISyntaxException {
        HashMap hash = new HashMap();
        Long value = 56564L;
        JsonValue json = JsonValue.of(56564L);
        String key = "key";
        hash.put(key,json);
        JsonMap map = JsonMap.of(hash);
        assertEquals(value, map.longValue(key));
    }

    @Test
    public void double_returns_double_value_when_map_has_double_value_for_key() throws URISyntaxException {
        HashMap hash = new HashMap();
        Double value = 56.64;
        JsonValue json = JsonValue.of(56.64);
        String key = "key";
        hash.put(key,json);
        JsonMap map = JsonMap.of(hash);
        assertEquals(value, map.doubleValue(key));
    }

    @Test
    public void boolean_returns_boolean_value_when_map_has_boolean_value_for_key() throws URISyntaxException {
        HashMap hash = new HashMap();
        Boolean value = true;
        JsonValue json = JsonValue.of(true);
        String key = "key";
        hash.put(key,json);
        JsonMap map = JsonMap.of(hash);
        assertEquals(value, map.booleanValue(key));
    }

    @Test
    public void map_returns_map_value_when_map_has_map_value_for_key() throws URISyntaxException {
        HashMap hash = new HashMap();
        JsonMap value = JsonMap.of(new HashMap());
        String key = "key";
        hash.put(key,value);
        JsonMap map = JsonMap.of(hash);
        assertEquals(value, map.map(key));
    }

    @Test
    public void list_returns_list_value_when_map_has_list_value_for_key() throws URISyntaxException {
        HashMap hash = new HashMap();
        JsonList value = JsonList.of(new ArrayList());
        String key = "key";
        hash.put(key,value);
        JsonMap map = JsonMap.of(hash);
        assertEquals(value, map.list(key));
    }

    private void assertNotEquals(int a, int b) {
        assertFalse(a==b);
    }

    private void assertNotEquals(Map a, Map b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
}