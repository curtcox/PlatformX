package c1.app;

import com.codename1.ui.Display;
import config.ShouldRun;
import org.junit.Before;
import x.app.Registry;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class C1ExceptionLoggerTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void can_access_via_factory() {
        assertNotNull(C1ExceptionLogger.of());
    }

    @Test
    public void can_be_installed() {
        Registry.put(Display.class,Display.getInstance());
        C1ExceptionLogger.of().install();
    }
}