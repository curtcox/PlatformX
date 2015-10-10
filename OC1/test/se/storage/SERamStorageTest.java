package se.storage;

import org.junit.Test;
import x.stores.XStorage;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SERamStorageTest {

    @Test
    public void can_create() {
        assertNotNull(new SERamStorage());
    }

    @Test
    public void is_XStorage() {
        assertTrue(new SERamStorage() instanceof XStorage);
    }
}