package se.services;

import org.junit.Test;
import x.services.XLocationProvider;

import static org.junit.Assert.assertTrue;

public class SELocationProviderTest {

    @Test
    public void is_a_LocationService() {
        assertTrue(new SELocationProvider() instanceof XLocationProvider);
    }


}