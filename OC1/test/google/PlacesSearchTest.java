package google;

import j2se.J2seNetwork;
import myapp.Registry;
import myapp.net.Network;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class PlacesSearchTest {
    
    PlacesSearch testObject;
    
    @Before
    public void setUp() {
        Registry.put(Network.class, new J2seNetwork());
        testObject = new PlacesSearch();
    }
    
    @Test
    public void can_create() {
        assertNotNull(new PlacesSearch());
    }

    @Test
    public void search_nearby_38_6269_neg_90_12697_contains_arch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100)) {
            if (place.name.contains("Jefferson National Expansion Memorial")) {
                assertEquals("34bf6d6750168b338183d3ff4914768348d58374",place.id);
                assertEquals("St Louis",place.vicinity);
                return;
            }
        }

        fail();
    }

    Place getArchUsingNearbySearch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100)) {
            if (place.name.contains("Jefferson National Expansion Memorial")) {
                return place;
            }
        }
        throw new RuntimeException();
    }
}
