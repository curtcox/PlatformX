package x.app;

import config.ShouldRun;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RegistryTest {

    @Before
    public void setUp() {
        Assume.assumeTrue(ShouldRun.X);
    }

    @Test
    public void test_get_throws_exception_when_no_value_for_key() {
        try {
            Registry.get(Set.class);
            fail();
        } catch (RuntimeException e) {
            String message = "No registered Set";
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void test_get_returns_single_put_item() {
        Map value = new HashMap();
        Class key = Map.class;

        Registry.put(key, value);
        
        assertSame(value, Registry.get(key));
    }

    @Test
    public void test_get_returns_two_put_items() {
        Map value1 = new HashMap();
        Class key1 = Map.class;
        List value2 = new ArrayList();
        Class key2 = List.class;
        
        Registry.put(key1,value1);
        Registry.put(key2, value2);
        
        assertSame(value1, Registry.get(key1));
        assertSame(value2, Registry.get(key2));
    }
    
}
