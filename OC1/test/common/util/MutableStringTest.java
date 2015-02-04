package common.util;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutableStringTest {

    @Test
    public void toString_returns_empty_string_when_made_from_zero_arg_constructor() {
        assertEquals("",new MutableString().toString());
    }

    @Test
    public void toString_returns_value_when_made_from_string_constructor() {
        String value = random();
        assertEquals(value,new MutableString(value).toString());
    }

    @Test
    public void toString_returns_value_when_set() {
        String value = random();

        MutableString testObject = new MutableString();
        testObject.set(value);

        assertEquals(value, testObject.toString());
    }

    private String random() {
        return toString();
    }

}