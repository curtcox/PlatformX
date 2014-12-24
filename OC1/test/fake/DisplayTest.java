package fake;

import com.codename1.ui.Display;
import org.junit.Test;
import static org.junit.Assert.*;

public class DisplayTest {

    @Test
    public void can_getInstance_returns_Display() {
        assertTrue(Display.getInstance() instanceof Display);
    }
}
