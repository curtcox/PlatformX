package x.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class SplitterTest {
    
    @Test
    public void split_returns_right_counts_for_no_delimiters() {
        assertEquals(1,Splitter.split("",     "").length);
        assertEquals(1,Splitter.split("xyz", "=").length);
        assertEquals(1,Splitter.split("x=y", " ").length);
    }

    @Test
    public void split_returns_right_values_for_x_equals_y() {
        assertEquals("x",Splitter.split("x=y","=")[0]);
        assertEquals("y",Splitter.split("x=y","=")[1]);
    }

    @Test
    public void split_returns_right_values_for_key_equals_value() {
        assertEquals("key",Splitter.split("key=y","=")[0]);
        assertEquals("value",Splitter.split("x=value","=")[1]);
    }

    @Test
    public void split_returns_right_values_for_x_y_z() {
        assertEquals("x",Splitter.split("x y z"," ")[0]);
        assertEquals("y",Splitter.split("x y z"," ")[1]);
        assertEquals("z",Splitter.split("x y z"," ")[2]);
    }

}
