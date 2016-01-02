package google;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class URLEncoderTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Google);
    }

    @Test
    public void encode() throws Exception {
        assertEncoding("Delmar","Delmar");
        assertEncoding("Delmar Loop","Delmar+Loop");
    }

    private void assertEncoding(String string, String encoded) throws Exception {
        assertEquals(encoded,URLEncoder.encode(string));
    }
}
