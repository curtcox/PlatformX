package x.ui;

import org.junit.Test;
import x.ui.AttributedString.Part;
import static x.ui.AttributedString.Decoration.*;
import static org.junit.Assert.*;

public class AttributedStringBuilderTest {
    
    @Test
    public void can_create() {
        assertTrue(new AttributedStringBuilder().build() instanceof AttributedString);
    }

    @Test
    public void toString_with_no_parts() {
        AttributedString string = new AttributedStringBuilder().build();
        assertEquals("",string.toString());
    }

    @Test
    public void no_parts() {
        AttributedString string = new AttributedStringBuilder().build();
        assertEquals(0,string.parts.size());
    }

    @Test
    public void iterator_hasNext_returns_false_for_no_parts() {
        AttributedString string = new AttributedStringBuilder().build();
        assertFalse(string.iterator().hasNext());
    }

    @Test
    public void simple_text_with_no_attributes() {
        AttributedString string = new AttributedStringBuilder()
                .append("text")
                .build(); 
        assertParts(string,new Part(null,null,null,"text"));
   }

    @Test
    public void one_part_red_text() {
        AttributedString string = new AttributedStringBuilder()
                .color(Color.RED)
                .append("text")
                .build(); 
        assertEquals("text",string.toString());
        assertParts(string,new Part(null,Color.RED,null,"text"));
    }

    @Test
    public void appended_text_with_2_parts_and_no_markup() {
        AttributedString string = new AttributedStringBuilder()
                .append("text ")
                .append("more text")
                .build(); 
        assertEquals("text more text",string.toString());
        assertParts(string,new Part(null,null,null,"text more text"));
    }

    @Test
    public void appended_text_with_3_parts_and_no_markup() {
        AttributedString string = new AttributedStringBuilder()
                .append("tinker ")
                .append("evars ")
                .append("chance")
                .build(); 
        assertEquals("tinker evars chance",string.toString());
        assertParts(string,new Part(null,null,null,"tinker evars chance"));
    }

    @Test
    public void appended_text_with_a_color_change_1() {
        AttributedString string = new AttributedStringBuilder()
                .append("text ")
                .color(Color.RED)
                .append("more text")
                .build(); 
        assertEquals("text more text",string.toString());
        assertParts(string,
            new Part(null,null,null,"text "),
            new Part(null,Color.RED,null,"more text"));
    }

    @Test
    public void appended_text_with_a_color_change_2() {
        AttributedString string = new AttributedStringBuilder()
                .append("text ")
                .color(Color.RED)
                .append("more ")
                .append("text")
                .build(); 
        assertEquals("text more text",string.toString());
        assertParts(string,
            new Part(null,null,null,"text "),
            new Part(null,Color.RED,null,"more text"));
    }

    @Test
    public void appended_text_with_2_color_changes() {
        AttributedString string = new AttributedStringBuilder()
                .append("USA ")
                .color(Color.RED)
                .append("Red ")
                .color(Color.BLUE)
                .append("Blue")
                .build(); 
        assertEquals("USA Red Blue",string.toString());
        assertParts(string,
            new Part(null,null,null,"USA "),
            new Part(null,Color.RED,null,"Red "),
            new Part(null,Color.BLUE,null,"Blue")
        );
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

    private void assertParts(AttributedString string, Part... parts) {
        assertEquals(parts.length,string.parts.size());
        for (int i=0; i<parts.length; i++) {
            assertEquals(parts[i],string.parts.get(i));
        }
    }
}
