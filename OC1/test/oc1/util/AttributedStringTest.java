package oc1.util;

import java.awt.Color;
import org.junit.Test;
import oc1.util.AttributedString.Attributes;
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
        assertSimple(string);
    }

    @Test
    public void empty_string_has_no_attributes_by_default() {
        AttributedString string = AttributedString.builder().build(); 
        assertEquals(null,string.attributes);
        assertSimple(string);
    }

    @Test
    public void empty_string_iteratable_returns_false_for_hasNext() {
        AttributedString string = AttributedString.builder().build(); 
        assertFalse(string.iterator().hasNext());
        assertSimple(string);
    }

    @Test
    public void homogenous_unattributed_text_string_is_simple() {
        AttributedString string = AttributedString.builder()
                .append("text")
                .build(); 
        assertEquals("text",string.toString());
        assertSimple(string);
    }

    @Test
    public void homogenous_attributed_text_string_is_simple() {
        AttributedString string = AttributedString.builder()
                .color(Color.RED)
                .append("text")
                .build(); 
        assertEquals("text",string.toString());
        assertEquals(Color.RED,string.attributes.color);
        assertSimple(string);
    }

    @Test
    public void appended_text_accumulates() {
        AttributedString string = AttributedString.builder()
                .append("text ")
                .append("more text")
                .build(); 
        assertEquals("text more text",string.toString());
        assertSimple(string);
    }

    @Test
    public void a_color_change_produces_a_compound_string() {
        AttributedString string = AttributedString.builder()
                .append("text ")
                .color(Color.RED)
                .append("more text")
                .build(); 
        assertEquals("text more text",string.toString());
        assertCompound(string);
    }

    @Test
    public void attributes_are_equal() {
        assertEqualAttributes(new Attributes(null,null),new Attributes(null,null));    
        assertEqualAttributes(new Attributes(null,Color.BLACK),new Attributes(null,Color.BLACK));    
    }

    @Test
    public void attributes_are_not_equal() {
        assertUnequalAttributes(new Attributes(null,Color.BLACK),new Attributes(null,Color.WHITE));    
    }

    private void assertEqualAttributes(Attributes a, Attributes b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    private void assertUnequalAttributes(Attributes a, Attributes b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    private void assertSimple(AttributedString string) {
        assertTrue(string.simple);
        assertFalse(string.iterator().hasNext());
    }

    private void assertCompound(AttributedString string) {
        assertFalse(string.simple);
        assertNull(string.attributes);
        assertTrue(string.iterator().hasNext());
    }

}
