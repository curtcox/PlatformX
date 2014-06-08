package oc1.stores;

import oc1.stores.MyRatings;
import com.codename1.io.Storage;
import fake.FakeStorage;
import oc1.Registry;
import oc1.domain.ID;
import oc1.domain.Rating;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MyRatingsTest {
    
    FakeStorage storage = new FakeStorage();
    MyRatings ratings1;
    
    @Before
    public void setUp() {
        Registry.put(Storage.class, storage);
        ratings1 = new MyRatings();
    }
    
    @Test
    public void test_getFor_returns_value_previously_put_in_storage() {
        ID id = new ID("" + hashCode());
        Rating rating = new Rating(toString());
        ratings1.put(id, rating);
        storage.inputStream = new ByteArrayInputStream(storage.outputStream.toByteArray());
        
        MyRatings ratings2 = new MyRatings();
        Rating actual = ratings2.getFor(id);
        
        assertEquals(rating,actual);
    }
    
}
