package va.app;

import com.codename1.io.Storage;
import config.ShouldRun;
import fake.FakeDeviceInfo;
import fake.FakeDisplay;
import fake.FakeStorage;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.pagefactories.KeyValuePairListSource;
import x.log.ILog;
import x.log.ILogManager;
import x.ui.IDisplay;

import static org.junit.Assume.assumeTrue;

public class VaRegistryLoaderTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Vaadin);
    }

    @Test
    public void load_with_fake_storage_and_raw_network() {
        loadPlatform();
    }

    @Test
    public void can_log_after_loading() {
        loadPlatform();
        log("Doesn't throw an exception. So we got that going for us.");
    }

    void loadPlatform() {
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(IDisplay.class, new FakeDisplay());
        Registry.put(KeyValuePairListSource.class, new FakeDeviceInfo());
        VaRegistryLoader.loadPlatform();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(VaRegistryLoaderTest.class);
    }

}