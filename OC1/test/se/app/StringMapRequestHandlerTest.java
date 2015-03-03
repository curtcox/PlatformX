package se.app;

import common.util.SimpleStringMap;
import common.util.StringMap;
import mite.RequestHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StringMapRequestHandlerTest {

    Map map = new HashMap();
    StringMap stringMap = new SimpleStringMap(map);
    StringMapRequestHandler testObject = new StringMapRequestHandler(stringMap);

    @Test
    public void can_create() {
        assertNotNull(new StringMapRequestHandler(null));
    }

    @Test
    public void is_a_RequestHandler() {
        assertTrue(new StringMapRequestHandler(null) instanceof RequestHandler);
    }

    @Test
    public void does_not_handle_requests_that_are_not_in_map() {
        assertFalse(testObject.handles("key not in map"));
    }

    @Test
    public void handles_requests_that_are_in_map() {
        map.put("key in map","stuff");

        assertTrue(testObject.handles("key in map"));
    }

}