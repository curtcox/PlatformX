package common.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class StringsTest {
    
    @Test
    public void split_returns_right_counts_for_no_delimiters() {
        assertEquals(1,Strings.split("",     "").length);
        assertEquals(1,Strings.split("xyz", "=").length);
        assertEquals(1,Strings.split("x=y", " ").length);
    }

    @Test
    public void split_returns_right_values_for_x_equals_y() {
        assertEquals("x",Strings.split("x=y","=")[0]);
        assertEquals("y",Strings.split("x=y","=")[1]);
    }

    @Test
    public void split_returns_right_values_for_key_equals_value() {
        assertEquals("key",Strings.split("key=y","=")[0]);
        assertEquals("value",Strings.split("x=value","=")[1]);
    }

    @Test
    public void split_returns_right_values_for_x_y_z() {
        assertEquals("x",Strings.split("x y z"," ")[0]);
        assertEquals("y",Strings.split("x y z"," ")[1]);
        assertEquals("z",Strings.split("x y z"," ")[2]);
    }

    @Test
    public void replace_returns_original_when_text_not_in_string() {
        assertEquals("fox",Strings.replace("fox", "_", "?"));
    }

    @Test
    public void replace_replaced_text_with_replacement() {
        assertEquals("what the fox",Strings.replace("what_the_fox", "_", " "));
    }

}
