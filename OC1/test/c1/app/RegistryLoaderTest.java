package c1.app;

import c1.net.RawNetwork;
import c1.services.ILocationManager;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import common.Registry;
import common.log.ILog;
import common.log.ILogManager;
import common.net.Network;
import common.ui.IDisplay;
import fake.FakeDisplay;
import fake.FakeLocationManager;
import fake.FakeStorage;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RegistryLoaderTest {

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
        Registry.put(Network.class, new RawNetwork());
        Registry.put(IDisplay.class, new FakeDisplay());
        Registry.put(ILocationManager.class, new FakeLocationManager());
        RegistryLoader.loadPlatform();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(RegistryLoaderTest.class);
    }

}