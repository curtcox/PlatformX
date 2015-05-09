package c1.app;

import fake.FakeC1RegistryLoader;
import junit.framework.TestCase;
import org.junit.Test;

public class HeartbeatTest {

    @Test
    public void can_create() {
        new Heartbeat();
    }

    @Test
    public void can_log() {
        FakeC1RegistryLoader.load();
        new Heartbeat().log();
    }

}