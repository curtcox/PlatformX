package fake;

import com.codename1.io.Storage;
import oc1.app.Registry;
import oc1.log.LogManager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class FakeRegistryLoaderTest {

    @Test
    public void can_load() {
        FakeRegistryLoader.load();
    }

    @Test
    public void load_puts_LogManager_in_Registry() {
        FakeRegistryLoader.load();
        assertTrue(Registry.get(LogManager.class) instanceof LogManager);
    }

    @Test
    public void load_puts_FakeStorage_in_Registry() {
        FakeRegistryLoader.load();
        assertTrue(Registry.get(Storage.class) instanceof FakeStorage);
    }

}
