package oc1.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class IOTest {
    
    @Test
    public void stringOrEmptyFrom_returns_expected_string() {
        String expected = "Stuff in the string";
        InputStream stream = new ByteArrayInputStream(expected.getBytes());
        
        String actual = IO.stringOrEmptyFrom(stream);
        
        assertEquals(expected,actual);
    }
    
}
