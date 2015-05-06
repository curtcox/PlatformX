package common.ui;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AttributedStringTest {

    @Test
    public void can_create_from_a_string() {
        assertNotNull(new AttributedString("",new ArrayList<AttributedString.Part>()));
    }

    @Test
    public void empty_string_constructed_from_0_args() {
        AttributedString testObject = new AttributedString();
        assertEquals(0,testObject.parts.size());
        assertEquals("",testObject.toString());
    }

    @Test
    public void empty_string_constructed_from_1_arg() {
        AttributedString testObject = new AttributedString("");
        assertEquals(0,testObject.parts.size());
        assertEquals("",testObject.toString());
    }

    @Test
    public void string_with_no_attributes() {
        String text = toString();

        AttributedString testObject = new AttributedString(text);

        assertEquals(1,testObject.parts.size());
        AttributedString.Part part = testObject.parts.get(0);
        assertEquals(text,part.text);
        assertEquals(null,part.color);
        assertEquals(null,part.font);
        assertEquals(null,part.decoration);
        assertEquals(text,testObject.toString());
    }

}