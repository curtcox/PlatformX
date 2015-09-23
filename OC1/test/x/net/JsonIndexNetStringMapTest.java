package x.net;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import x.json.XJSON;
import config.ShouldRun;
import x.uiwidget.XImage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;

public class JsonIndexNetStringMapTest {

    Map<URI,String> pages = new HashMap<URI,String>();
    
    Network network = new Network() {
        public InputStream getStreamFor(URI uri) {
            return new ByteArrayInputStream(pages.get(uri).getBytes());
        }
        public XImage getImage(URI uri)               { throw new UnsupportedOperationException(); }
        public XImage getImage(URI uri, int w, int h) { throw new UnsupportedOperationException(); }
    };
    
    URI index = URI("http://example.com/");
    URI absoulteUrlValuePage = URI("http://absolute/value_page");
    URI relativeUrlValuePage = URI("relative_value_page");
    String absoluteValueKey = "Vodka";
    String relativeValueKey = "Spencer";
    String absoluteValue = "0 Kelvin";
    String relativeValue = "warmish";
    JsonIndexNetStringMap testObject = new JsonIndexNetStringMap(index, XJSON.STRING_MAP_PARSER,network);
    
    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
        String indexJSON = JSON(
            "{",
                "'Vodka':'http://absolute/value_page' ,",
                "'Spencer':'relative_value_page' ",
            "}"
        ); 
        pages.put(index, indexJSON);
        pages.put(absoulteUrlValuePage, absoluteValue);
        pages.put(URI("http://example.com/relative_value_page"), relativeValue);
    }

    private String JSON(String... lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line);
        }
        return out.toString().replaceAll("'", "\"");
    }
    
    @Test
    public void get_uses_network_to_return_value_from_absolute_URL() {
        String expected = absoluteValue;
        
        String actual = testObject.get(absoluteValueKey);
        
        assertEquals(expected,actual);
    }

    @Test
    public void get_uses_network_to_return_value_from_relative_URL() {
        String expected = relativeValue;
        
        String actual = testObject.get(relativeValueKey);
        
        assertEquals(expected,actual);
    }

    private URI URI(String string) {
        try {
            return new URI(string);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
