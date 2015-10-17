package x.json;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class JsonRendererTest {

    @Test
    public void list_returns_empty_list_when_given_no_args() {
        assertTrue(JsonRenderer.list().isEmpty());
    }

    @Test
    public void list_returns_JsonList_containing_all_json() {
        JsonList list = JsonRenderer.list("string",true,false,7L,676.6767);
        assertEquals(5,list.size());
        for (Json json : list) {
            assertNotNull(json);
        }
    }

    @Test
    public void list_returns_JsonList_with_corresponding_Json_values() {
        List list = Arrays.asList("string", true, false, 7L, 676.6767);
        JsonList json = JsonRenderer.list(list);
        for (int i=0; i<5; i++) {
            JsonValue value = (JsonValue) json.get(i);
            assertEquals(list.get(i),value.value());
        }
    }

    @Test
    public void object_returns_object_itself_if_it_is_already_Json() {
        Json value = JsonValue.of("whatever");
        assertSame(value, JsonRenderer.object(value));
    }

    @Test
    public void map_returns_empty_map_when_given_empty_map() {
        assertTrue(JsonRenderer.map(new HashMap()).isEmpty());
    }

    @Test
    public void map_returns_JsonMap_containing_all_json() {
        HashMap hash = new HashMap();
        hash.put("a","string");
        hash.put("b",true);
        hash.put("c",false);
        hash.put("d",7L);
        hash.put("e",676.6767);
        JsonMap map = JsonRenderer.map(hash);
        assertEquals(5, map.size());
        for (Json json : map.values()) {
            assertNotNull(json);
        }
    }

}