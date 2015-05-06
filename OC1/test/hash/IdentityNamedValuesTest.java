package hash;

import org.junit.Test;
import static org.junit.Assert.*;

public class IdentityNamedValuesTest {
    
    IdentityNamedValues testObject = new IdentityNamedValues();
    
    @Test
    public void get_returns_expression_that_evaluates_to_given_value() {
        String expected = "random value";
        
        Object actual = testObject.get(expected).invokeIn(null);
        
        assertEquals(expected,actual);
    }
    
}
