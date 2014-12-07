package oc1.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class AttributedStringTest {
    
    @Test
    public void can_create() {
        assertTrue(AttributedString.builder().build() instanceof AttributedString);
    }

    @Test
    public void empty_string_is_simple_by_default() {
        AttributedString string = AttributedString.builder().build(); 
        assertEquals("",string.toString());
        assertTrue(string.simple);
    }

    @Test
    public void text_string_is_simple_by_default() {
        AttributedString string = AttributedString.builder()
                .append("text")
                .build(); 
        assertEquals("text",string.toString());
        assertTrue(string.simple);
    }

}
