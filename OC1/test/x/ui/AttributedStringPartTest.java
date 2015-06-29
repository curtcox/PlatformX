package x.ui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AttributedStringPartTest {

    @Test
    public void equivalent_parts_are_equal() {
        assertAreEqual(new AttributedString.Part(null,null,null,null),new AttributedString.Part(null,null,null,null));
        assertAreEqual(new AttributedString.Part(null,null,null,"foo"),new AttributedString.Part(null,null,null,"foo"));
    }

    @Test
    public void new_line() {
        assertAreEqual(new AttributedString.Part(null,null,null,"/n"),AttributedString.NEW_LINE);
    }

    private void assertAreEqual(AttributedString.Part a, AttributedString.Part b) {
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertEquals(a.hashCode(),b.hashCode());
    }
}
