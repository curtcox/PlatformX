package google;

import fake.FakeRegistryLoader;
import j2se.J2seNetwork;
import oc1.app.Registry;
import oc1.log.LogManager;
import oc1.log.LogWriter;
import oc1.net.Network;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class PlacesSearchTest {
    
    String[] all = new String[0];
    PlacesSearch testObject;
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
        Registry.put(Network.class,   new J2seNetwork());
        testObject = new PlacesSearch();
    }
    
    @Test
    public void can_create() {
        assertNotNull(new PlacesSearch());
    }

    @Test
    public void search_nearby_38_6269_neg_90_12697_contains_arch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100,all)) {
            if (place.name.contains("Jefferson National Expansion Memorial")) {
                assertEquals("ChIJWwMk5eSy2IcR_VqVShp8iW0",place.id);
                assertEquals("St Louis",place.vicinity);
                return;
            }
        }

        fail();
    }

    Place getArchUsingNearbySearch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100,all)) {
            if (place.name.contains("Jefferson National Expansion Memorial")) {
                return place;
            }
        }
        throw new RuntimeException();
    }
}
