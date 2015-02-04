package common.util;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class SimpleMutableStringMapTest {

    SimpleMutableStringMap testObject = new SimpleMutableStringMap();

    @Test
    public void get_returns_value_put_for_single_key() {
        String key = random("key");
        String value = random("value");

        testObject.put(key,value);

        assertSame(value, testObject.get(key));
    }

    @Test
    public void get_returns_value_put_for_two_keys() {
        String key1 = random("key1");
        String value1 = random("value1");
        String key2 = random("key2");
        String value2 = random("value2");

        testObject.put(key1,value1);
        testObject.put(key2,value2);

        assertSame(value1, testObject.get(key1));
        assertSame(value2, testObject.get(key2));
    }

    @Test
    public void get_returns_null_when_no_value_set_for_key() {
        String key = random("key");
        assertSame(null, testObject.get(key));
    }

    private String random(String key) {
        return key + toString();
    }
}