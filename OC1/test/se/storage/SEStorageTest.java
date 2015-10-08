package se.storage;

import org.junit.Test;
import x.stores.XStorage;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SEStorageTest {

    @Test
    public void can_create() {
        assertNotNull(new SEStorage());
    }

    @Test
    public void is_XStorage() {
        assertTrue(new SEStorage() instanceof XStorage);
    }
}