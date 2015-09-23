package se.services;

import org.junit.Test;
import x.services.XLocationService;

import static org.junit.Assert.assertTrue;

public class SELocationServiceTest {

    @Test
    public void is_a_LocationService() {
        assertTrue(new SELocationService() instanceof XLocationService);
    }


}