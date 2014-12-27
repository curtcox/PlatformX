package oc1.io;

import fake.FakeRegistryLoader;
import oc1.JSON;
import oc1.util.StringMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Curt
 */
public class JSONTest {
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void stringMapFrom_returns_StringMap_without_nuts() {
        StringMap actual = JSON.STRING_MAP_PARSER.parse("{}");
        assertTrue(actual.get("nuts")==null);
    }    

    @Test
    public void stringMapFrom_returns_StringMap_with_nuts() {
        StringMap actual = JSON.STRING_MAP_PARSER.parse("{ 'nuts': 'Skippy' }".replaceAll("'","\""));
        assertEquals("Skippy",actual.get("nuts"));
    }    

    @Test
    public void stringMapFrom_returns_StringMap_for_JSON_with_2_pairs() {
        StringMap actual = JSON.STRING_MAP_PARSER.parse(
             "{ 'nuts': 'Skippy', 'red': 'dwarf' }"
             .replaceAll("'","\""));
        assertEquals("Skippy",actual.get("nuts"));
        assertEquals("dwarf",actual.get("red"));
    }    

}
