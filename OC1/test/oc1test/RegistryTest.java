package oc1test;

import oc1.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class RegistryTest {

    @Test
    public void test_get_throws_exception_when_no_value_for_key() {
        try {
            Registry.get(Set.class);
            fail();
        } catch (RuntimeException e) {
            String message = "No registered Set";
            assertEquals(message,e.getMessage());
        }
    }

    @Test
    public void test_get_returns_single_put_item() {
        Map value = new HashMap();
        Class key = Map.class;
        
        Registry.put(key,value);
        
        assertSame(value,Registry.get(key));
    }

    @Test
    public void test_get_returns_two_put_items() {
        Map value1 = new HashMap();
        Class key1 = Map.class;
        List value2 = new ArrayList();
        Class key2 = List.class;
        
        Registry.put(key1,value1);
        Registry.put(key2,value2);
        
        assertSame(value1,Registry.get(key1));
        assertSame(value2,Registry.get(key2));
    }
    
}
