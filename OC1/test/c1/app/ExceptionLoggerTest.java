package c1.app;

import com.codename1.ui.Display;
import common.Registry;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ExceptionLoggerTest {

    @Test
    public void can_access_via_factory() {
        assertNotNull(ExceptionLogger.of());
    }

    @Test
    public void can_be_installed() {
        Registry.put(Display.class,Display.getInstance());
        ExceptionLogger.of().install();
    }
}