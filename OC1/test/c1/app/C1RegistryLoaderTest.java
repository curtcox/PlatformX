package c1.app;

import c1.net.C1RawNetwork;
import c1.services.ILocationManager;
import config.ShouldRun;
import fake.FakeDeviceInfo;
import fake.FakeDisplay;
import fake.FakeLocationManager;
import fake.FakeStorage;
import org.junit.Before;
import org.junit.Test;
import x.Registry;
import x.event.NamedValueListSource;
import x.log.ILog;
import x.log.ILogManager;
import x.net.Network;
import x.stores.XStorage;
import x.ui.IDisplay;

import static org.junit.Assume.assumeTrue;

public class C1RegistryLoaderTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
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
        Registry.put(XStorage.class, new FakeStorage());
        Registry.put(Network.class, new C1RawNetwork());
        Registry.put(IDisplay.class, new FakeDisplay());
        Registry.put(ILocationManager.class, new FakeLocationManager());
        Registry.put(NamedValueListSource.class, new FakeDeviceInfo());
        C1RegistryLoader.loadPlatform();
    }

    private void log(String message) {
        getLog().log(message);
    }

    private ILog getLog() {
        return Registry.get(ILogManager.class).getLog(C1RegistryLoaderTest.class);
    }

}