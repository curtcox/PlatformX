package oc1.services;

import com.codename1.location.Location;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class LocationQuantizerTest {
    
    LocationQuantizer testObject = new LocationQuantizer();

    @Test
    public void test_can_create() {
        assertNotNull(new LocationQuantizer());
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
        Location in = new Location();
        in.setLatitude(lat_in);
        in.setLongitude(lon_in);
        Location out = testObject.quantize(in);
        
        assertEquals(lat_out,out.getLatitude(), 0.0000001);
        assertEquals(lon_out,out.getLongitude(),0.0000001);
    }

}
