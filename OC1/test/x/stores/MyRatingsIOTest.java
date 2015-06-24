package x.stores;

import x.domain.ID;
import x.domain.Rating;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MyRatingsIOTest {
    
    MyRatingsIO testObject = new MyRatingsIO();
    
    @Test
    public void readKey_returns_expected_key() {
        ID expected = new ID(random());
        ID actual = (ID) testObject.readKey(expected + "=whatever_value");
        assertEquals(expected,actual);
    }

    @Test
    public void readValue_returns_expected_value() {
        Rating expected = new Rating(random());
        Rating actual = (Rating) testObject.readValue("whatever_key=" + expected);
        assertEquals(expected,actual);
    }

    @Test
    public void writePair_starts_with_key_string() {
        ID key = new ID(random());
        Rating value = new Rating("??");
        assertTrue(testObject.writePair(key, value).startsWith(key.toString()));
    }

    @Test
    public void writePair_ends_with_value_string() {
        ID key = new ID("??");
        Rating value = new Rating(random());
        assertTrue(testObject.writePair(key, value).endsWith(value.toString()));
    }

    private String random() {
        return toString();
    }
    
}
