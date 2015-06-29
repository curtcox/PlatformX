package google;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class URLEncoderTest {
    
    @Test
    public void encode() throws Exception {
        assertEncoding("Delmar","Delmar");
        assertEncoding("Delmar Loop","Delmar+Loop");
    }

    private void assertEncoding(String string, String encoded) throws Exception {
        assertEquals(encoded,URLEncoder.encode(string));
    }
}
