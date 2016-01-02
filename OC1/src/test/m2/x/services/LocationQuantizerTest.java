package x.services;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class LocationQuantizerTest {
    
    XLocationQuantizer testObject = new XLocationQuantizer();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void test_can_create() {
        assertNotNull(new XLocationQuantizer());
    }
    
    @Test
    public void origin_is_returned_as_given() {
        assertConvertedTo(0.0,0.0,   0.0,0.0);
    }

    @Test
    public void latitude_is_rounded_to_the_nearest_001() {
        assertConvertedTo(00.0070,0.0,   00.007,0.0);
        assertConvertedTo(00.0073,0.0,   00.007,0.0);
        assertConvertedTo(12.3456,0.0,   12.346,0.0);
    }

    @Test
    public void longitude_is_rounded_to_the_nearest_001() {
        assertConvertedTo(0.0,00.0070,    0.0,00.007);
        assertConvertedTo(0.0,00.0073,    0.0,00.007);
        assertConvertedTo(0.0,12.3456,    0.0,12.346);
    }

    private void assertConvertedTo(double lat_in, double lon_in, double lat_out, double lon_out) {
        XLocation in = new XLocation(lat_in,lon_in);
        XLocation out = testObject.quantize(in);
        
        assertEquals(lat_out,out.latitude, 0.0000001);
        assertEquals(lon_out,out.longitude,0.0000001);
    }

}
