package c1.app;

import com.codename1.ui.Display;
import x.Registry;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class C1ExceptionLoggerTest {

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