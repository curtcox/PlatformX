package c1.app;

import c1.net.RawNetwork;
import c1.services.ILocationManager;
import com.codename1.io.Storage;
import common.Registry;
import common.net.Network;
import common.ui.IDisplay;
import fake.FakeDisplay;
import fake.FakeLocationManager;
import fake.FakeStorage;
import junit.framework.TestCase;
import org.junit.Test;

public class RegistryLoaderTest {

    @Test
    public void load_with_fake_storage_and_raw_network() {
        Registry.put(Storage.class, new FakeStorage());
        Registry.put(Network.class, new RawNetwork());
        Registry.put(IDisplay.class, new FakeDisplay());
        Registry.put(ILocationManager.class, new FakeLocationManager());
        RegistryLoader.loadPlatform();
    }
}