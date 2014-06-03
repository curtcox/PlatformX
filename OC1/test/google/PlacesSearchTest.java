package google;

import j2se.J2seNetwork;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.net.Network;
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
        new PlacesSearch();
    }

    @Test
    public void places_search_of_38_6269_neg_90_12697_conatins_arch() {
        for (Place place : testObject.nearbySearch(38.6269d, -90.18697d, 100)) {
            if (place.name.contains("Jefferson National Expansion Memorial")) {
                return;
            }
        }

        fail();
    }
}
