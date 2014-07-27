package google;

import fake.FakeRegistryLoader;
import j2se.J2seNetwork;
import oc1.app.Registry;
import oc1.net.Network;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class GeocodingTest {
    
    String[] all = new String[0];
    Geocoding testObject;
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
        Registry.put(Network.class,   new J2seNetwork());
        testObject = new Geocoding();
    }
    
    @Test
    public void can_create() {
        assertNotNull(new Geocoding());
    }

    @Test
    public void search_for_Chicago_contains_Chicago() {
        for (Location location : testObject.searchFor("Chicago")) {
            if (location.address.contains("Chicago, IL, USA")) {
                double delta = 0.01;
                assertEquals(41.8781136,   location.latitude,delta);
                assertEquals(-87.6297982,  location.longitude,delta);
                assertEquals("APPROXIMATE",location.type);
                return;
            }
        }

        fail();
    }

//    formatted_address: "Chicago, IL, USA",
//    lat: 41.8781136,
//    lng: -87.6297982
//    location_type: "APPROXIMATE",
}
