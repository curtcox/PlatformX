package x.json;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonValueTest {

    @Test
    public void is_Json() {
        assertTrue(JsonValue.of(null) instanceof Json);
    }

    @Test
    public void toString_returns_string_given() {
        String value = toString();
        assertSame(value,JsonValue.of(value).toString());
    }

    @Test
    public void equal_values_are_equal() {
        assertEqualValues("");
        assertEqualValues("cat");
        assertEqualValues("dog");
    }

    @Test
    public void unequal_values_are_not_equal() {
        assertNotEqualValues("");
        assertNotEqualValues("cat");
        assertNotEqualValues("dog");
    }

    private void assertEqualValues(String s) {
        assertEquals(JsonValue.of(s).hashCode(), JsonValue.of(s).hashCode());
        assertEquals(JsonValue.of(s), JsonValue.of(s));
    }

    private void assertNotEqualValues(String s) {
        String other = s + "face";
        assertTrue(JsonValue.of(s).hashCode() != JsonValue.of(other).hashCode());
        assertFalse(JsonValue.of(s).equals(JsonValue.of(other)));
        assertFalse(JsonValue.of(other).equals(JsonValue.of(s)));
    }

    @Test
    public void double_value_returns_double_from_factory() {
        String input = toString();
        Double value = 3.1415926535;
        JsonValue json = JsonValue.of(input,value);
        assertEquals(value,json.doubleValue());
        assertSame(input,json.toString());
        assertEquals(value, json.value());
    }

    @Test
    public void long_value_returns_long_from_factory() {
        String input = toString();
        Long value = 31415926535L;
        JsonValue json = JsonValue.of(input,value);
        assertEquals(value,json.longValue());
        assertSame(input, json.toString());
        assertEquals(value, json.value());
    }

    @Test
    public void boolean_value_returns_boolean_from_factory() {
        String input = toString();
        Boolean value = true;
        JsonValue json = JsonValue.of(input,value);
        assertEquals(value,json.booleanValue());
        assertSame(input, json.toString());
        assertEquals(value, json.value());
    }

    @Test
    public void of_object_uses_string_when_string() {
        Object string = toString();
        JsonValue json = JsonValue.of(string);
        assertEquals(string,json.toString());
    }

    @Test
    public void of_object_uses_double_when_double() {
        Object value = 2.718281828;
        JsonValue json = JsonValue.of(value);
        assertEquals(value, json.doubleValue());
        assertEquals("2.718281828",json.toString());
    }

    @Test
    public void of_object_uses_long_when_long() {
        Object value = 2718281828L;
        JsonValue json = JsonValue.of(value);
        assertEquals(value,json.longValue());
        assertEquals("2718281828",json.toString());
    }

    @Test
    public void of_object_uses_boolean_when_boolean() {
        Object value = false;
        JsonValue json = JsonValue.of(value);
        assertEquals(value,json.booleanValue());
        assertEquals("false",json.toString());
    }

    @Test
    public void of_throws_exception_when_unsupported() {
        Object value = new Object();
        try {
            JsonValue.of(value);
            fail();
        } catch (IllegalArgumentException e) {
            String message = Object.class.toString();
            assertEquals(message,e.getMessage());
        }
    }

}