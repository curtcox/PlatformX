package hash;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class IdentityNamedValuesTest {
    
    IdentityNamedValues testObject = new IdentityNamedValues();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void get_returns_expression_that_evaluates_to_given_value() {
        String expected = "random value";
        
        Object actual = testObject.get(expected).invokeIn(null);
        
        assertEquals(expected,actual);
    }
    
}
