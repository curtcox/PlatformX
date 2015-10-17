package x.json;

import org.junit.Test;

import java.util.HashMap;
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
    public void equal_maps_are_equal() {
        mapsAreEqual(new HashMap());
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

}