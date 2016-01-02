package google;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import j2se.J2seNetwork;
import x.app.Registry;
import x.net.Network;
import x.util.Strings;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Ignore;
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
        assumeTrue(ShouldRun.CodenameOne);
        FakeC1RegistryLoader.load();
        Registry.put(Network.class,   new J2seNetwork());
        testObject = new PlacesSearch();
    }
    
    @Test
    public void can_create() {
        assertNotNull(new PlacesSearch());
    }

    @Test @Ignore
    public void search_nearby_38_6269_neg_90_12697_contains_arch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100,all)) {
            if (Strings.contains(place.name,"Jefferson National Expansion Memorial")) {
                assertEquals("ChIJWwMk5eSy2IcR_VqVShp8iW0",place.id);
                assertEquals("St Louis",place.vicinity);
                return;
            }
        }

        fail();
    }

    Place getArchUsingNearbySearch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100,all)) {
            if (Strings.contains(place.name,"Jefferson National Expansion Memorial")) {
                return place;
            }
        }
        throw new RuntimeException();
    }
}
