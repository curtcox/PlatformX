package c1.app;

import config.ShouldRun;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assume.assumeTrue;

public class HeartbeatTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

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