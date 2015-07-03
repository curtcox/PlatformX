package fake;

import com.codename1.io.Storage;
import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.log.ILogManager;
import x.log.XLogManager;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class FakeRegistryLoaderTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

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
