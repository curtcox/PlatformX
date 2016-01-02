package fake;

import com.codename1.ui.Display;
import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class DisplayTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void can_getInstance_returns_Display() {
        assertTrue(Display.getInstance() instanceof Display);
    }
}
