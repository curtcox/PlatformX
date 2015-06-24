package se.app;

import x.util.SimpleStringMap;
import x.util.StringMap;
import mite.HTTPRequest;
import mite.RequestHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

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
        assertFalse(testObject.handles("GET /key_not_in_map"));
    }

    @Test
    public void handles_requests_that_are_in_map() {
        HTTPRequest request = HTTPRequest.parse("GET /key_in_map");
        map.put("/key_in_map","stuff");

        assertTrue(testObject.handles(request));
    }

    @Test
    public void returns_map_value_for_requests_that_are_in_map() throws IOException {
        HTTPRequest request = HTTPRequest.parse("GET /key_in_map");
        String value = random();
        map.put("/key_in_map",value);

        assertEquals(value, testObject.handle(request));
    }

    private String random() {
        return toString();
    }

}