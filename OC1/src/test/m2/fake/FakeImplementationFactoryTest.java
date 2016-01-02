package fake;

import com.codename1.impl.CodenameOneImplementation;
import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class FakeImplementationFactoryTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

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
