package oc1.util;

import java.awt.Color;
import org.junit.Test;
import oc1.util.AttributedString.Part;
import static oc1.util.AttributedString.Decoration.*;
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
    }

    @Test
    public void empty_string_has_no_parts() {
        AttributedString string = AttributedString.builder().build(); 
        assertEquals(0,string.parts.size());
    }

    @Test
    public void empty_string_iteratable_returns_false_for_hasNext() {
        AttributedString string = AttributedString.builder().build(); 
        assertFalse(string.iterator().hasNext());
    }

    @Test
    public void homogenous_unattributed_text_string_is_simple() {
        AttributedString string = AttributedString.builder()
                .append("text")
                .build(); 
        assertEquals("text",string.toString());
   }

    @Test
    public void homogenous_attributed_text_string_has_one_part() {
        AttributedString string = AttributedString.builder()
                .color(Color.RED)
                .append("text")
                .build(); 
        assertEquals("text",string.toString());
        assertEquals(Color.RED,string.parts.get(0).color);
    }

    @Test
    public void appended_text_accumulates() {
        AttributedString string = AttributedString.builder()
                .append("text ")
                .append("more text")
                .build(); 
        assertEquals("text more text",string.toString());
    }

    @Test
    public void a_color_change_produces_a_compound_string() {
        AttributedString string = AttributedString.builder()
                .append("text ")
                .color(Color.RED)
                .append("more text")
                .build(); 
        assertEquals("text more text",string.toString());
    }

    @Test
    public void parts_are_equal() {
        assertEqualAttributes(new Part(null,null,null,null),new Part(null,null,null,null));    
        assertEqualAttributes(new Part(null,Color.BLACK,None,""),new Part(null,Color.BLACK,None,""));    
    }

    @Test
    public void parts_are_not_equal() {
        assertUnequalAttributes(new Part(null,Color.BLACK,None,""),new Part(null,Color.WHITE,None,""));    
    }

    private void assertEqualAttributes(Part a, Part b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }

    private void assertUnequalAttributes(Part a, Part b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

}
