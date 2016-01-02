package c1;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;
import x.json.XJSON;
import x.util.StringMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class XJSONTest {
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeC1RegistryLoader.load();
    }

    @Test
    public void stringMapFrom_returns_StringMap_without_nuts() {
        StringMap actual = XJSON.STRING_MAP_PARSER.parse("{}");
        assertTrue(actual.get("nuts")==null);
    }    

    @Test
    public void stringMapFrom_returns_StringMap_with_nuts() {
        StringMap actual = XJSON.STRING_MAP_PARSER.parse("{ 'nuts': 'Skippy' }".replaceAll("'","\""));
        assertEquals("Skippy",actual.get("nuts"));
    }    

    @Test
    public void stringMapFrom_returns_StringMap_for_JSON_with_2_pairs() {
        StringMap actual = XJSON.STRING_MAP_PARSER.parse(
             "{ 'nuts': 'Skippy', 'red': 'dwarf' }"
             .replaceAll("'","\""));
        assertEquals("Skippy",actual.get("nuts"));
        assertEquals("dwarf",actual.get("red"));
    }    

}
