package fake;

import com.codename1.impl.CodenameOneImplementation;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class FakeImplementationFactoryTest {
    
    @Test
    public void createImplementation_returns_a_CodenameOneImplementation() {
        FakeImplementationFactory testObject = new FakeImplementationFactory();
        assertTrue(testObject.createImplementation() instanceof CodenameOneImplementation);
    }

    @Test
    public void createImplementation_returns_FakeCodenameOneImplementation() {
        FakeImplementationFactory testObject = new FakeImplementationFactory();
        assertTrue(testObject.createImplementation() instanceof FakeCodenameOneImplementation);
    }
}
