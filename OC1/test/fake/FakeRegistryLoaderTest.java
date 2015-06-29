package fake;

import com.codename1.io.Storage;
import x.Registry;
import x.log.XLogManager;
import x.log.ILogManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FakeRegistryLoaderTest {

    @Test
    public void can_load() {
        FakeC1RegistryLoader.load();
    }

    @Test
    public void can_initDisplay() {
        FakeC1RegistryLoader.initDisplay();
    }

    @Test
    public void load_puts_LogManager_in_Registry() {
        FakeC1RegistryLoader.load();
        assertTrue(Registry.get(ILogManager.class) instanceof XLogManager);
    }

    @Test
    public void load_puts_FakeStorage_in_Registry() {
        FakeC1RegistryLoader.load();
        assertTrue(Registry.get(Storage.class) instanceof FakeStorage);
    }

}
