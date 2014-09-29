package oc2.net;

import com.codename1.ui.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import oc1.net.Network;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Curt
 */
public class NetStringMapTest {

    Map<URI,String> pages = new HashMap<URI,String>();
    
    Network network = new Network() {
        public InputStream getStreamFor(URI uri) {
            return new ByteArrayInputStream(pages.get(uri).getBytes());
        }
        public Image getImage(URI uri) { throw new UnsupportedOperationException(""); }
    };
    
    URI index = URI("http://example.com/");
    URI valuePage = URI("http://value_page/");
    String key = "key";
    String value = "value";
    NetStringMap testObject = new NetStringMap(index,network);
    
    @Before
    public void setUp() {
        String indexJSON = "{ 'key':'http://value_page/'}".replaceAll("'", "\""); 
        pages.put(index, indexJSON);
        pages.put(valuePage, value);
    }
    
    @Test
    public void get_uses_network_to_return_value() {
        String expected = "value";
        
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
