package x.net;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import x.uiwidget.XImage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SimpleNetStringMapTest {

    Map<URI,String> pages = new HashMap<URI,String>();
    
    Network network = new Network() {
        public InputStream getStreamFor(URI uri) {
            return new ByteArrayInputStream(pages.get(uri).getBytes());
        }
        public XImage getImage(URI uri) { throw new UnsupportedOperationException(""); }
        public XImage getImage(URI uri, int w, int h) { throw new UnsupportedOperationException(); }
    };
    
    URI base = URI("http://example.com/");
    URI valuePage = URI("http://example.com/Vodka");
    String key = "Vodka";
    String value = "0 Kelvin";
    String relativeValue = "warmish";
    SimpleNetStringMap testObject = new SimpleNetStringMap(base,network);
    
    @Before
    public void setUp() {
        pages.put(valuePage, value);
        pages.put(URI("http://example.com/relative_value_page"), relativeValue);
    }
    
    @Test
    public void get_uses_network_to_return_value_from_key() {
        String expected = value;
        
        String actual = testObject.get(key);
        
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
