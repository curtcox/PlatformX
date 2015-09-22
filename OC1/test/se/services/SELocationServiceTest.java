package se.services;

import org.junit.Test;
import x.services.LocationService;

import static org.junit.Assert.assertTrue;

public class SELocationServiceTest {

    @Test
    public void is_a_LocationService() {
        assertTrue(new SELocationService() instanceof LocationService);
    }
}