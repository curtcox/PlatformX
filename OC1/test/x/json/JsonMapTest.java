package x.json;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        hash.put("key","value");
        JsonMap map = JsonMap.of(hash);
        assertFalse(map.containsValue("key"));
        assertTrue(map.containsValue("value"));
    }

}