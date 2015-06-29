package fake;

import com.codename1.impl.CodenameOneImplementation;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class FakeCodenameOneImplementationTest {
    
    @Test
    public void is_a_CodenameOneImplementation() {
        assertTrue(new FakeCodenameOneImplementation() instanceof CodenameOneImplementation);
    }
}
