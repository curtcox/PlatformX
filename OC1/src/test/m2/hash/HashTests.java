package hash;
import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

public class HashTests {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void get_returns_null_value_when_none_found() {
        Hash hash = new Hash();
        assertEquals(null,hash.get("name_of_method_that_does_not_exist"));
    }

    @Test
    public void invoke_returns_null_value_when_method_not_found() {
        Hash hash = new Hash();
        String methodName = "foo";
        try {
            hash.invoke(methodName,null,null);
            fail();
        } catch (RuntimeException e) {
            assertEquals("foo not found in []",e.getMessage());
        }
    }

}
