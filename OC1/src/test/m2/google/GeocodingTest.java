package google;

import fake.FakeC1RegistryLoader;
import j2se.J2seNetwork;
import x.app.Registry;
import x.net.Network;
import x.util.Strings;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GeocodingTest {
    
    String[] all = new String[0];
    Geocoding testObject;
    
    @Before
    public void setUp() {
        FakeC1RegistryLoader.load();
        Registry.put(Network.class,   new J2seNetwork());
        testObject = new Geocoding();
    }
    
    @Test
    public void can_create() {
        assertNotNull(new Geocoding());
    }

    @Test @Ignore
    public void search_for_Chicago_contains_Chicago() {
        for (GoogleLocation location : testObject.searchFor("Chicago")) {
            if (Strings.contains(location.address,"Chicago, IL, USA")) {
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
